import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class Implements a file System
 */
public class FileSystem {
    int[] fileAllocAr;
    ArrayList<iNode> iNodeList;
    Bitmap bMap;
    int totalSize;

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        FileSystem fs = new FileSystem();
        String usrIn[] = {""};

        while(!usrIn[0].equals("exit")){
            usrIn =  in.nextLine().split(" ");
            switch (usrIn[0]){
                case("put"):
                    if(usrIn.length == 3) {
                        try {
                            if (fs.findFile(usrIn[1]) != -1) {
                                System.out.println("Filename already exists");
                            } else if (!fs.insert(new iNode(usrIn[1], Integer.parseInt(usrIn[2]))))
                                System.out.println("File " + usrIn[1] + " not inserted");
                        } catch (NumberFormatException ex) {
                            System.out.println("Size must be a number");
                        }
                    }else
                        System.out.println("Missing commands");
                    break;
                case("del"):
                    if(usrIn.length ==2) {
                        if (!fs.delete(usrIn[1]))
                            System.out.println("File name not found");
                    }else
                        System.out.println("Missing commands");
                    break;
                case("bitmap"):
                    fs.bMap.printBMap();
                    break;
                case("inodes"):
                    System.out.println(fs.toString());
                    break;
            }

        }
    }
    public FileSystem(){
        totalSize = 64;
        fileAllocAr = new int[totalSize];
        iNodeList = new ArrayList<>();
        bMap = new Bitmap();
        for(int i=0;i<fileAllocAr.length;i++){
            fileAllocAr[i] = -2;
        }
    }
    public boolean delete(String name){
        int index = this.findFile(name);
        if(index != -1 ) {
            iNode temp = this.iNodeList.get(index);
            int num = temp.getStart();
            int contents = fileAllocAr[num];
            this.bMap.makeZero(num);

            num = contents;

            while (contents != -1) {
                contents = fileAllocAr[num];
                this.bMap.makeZero(num);
                num = contents;
            }
            this.totalSize += temp.getSize();
            this.iNodeList.remove(index);
            return true;
        }
        else return false;

    }
    /**
     * Function inserts a node if there's enough size in the Allocation Array to add the file
     * Calls the bitMap to see where the empty spots are located.
     * @param node -
     * @return
     */
    public boolean insert(iNode node){
        //makes sure we will have space for the node
        if(node.getSize() <= totalSize){
            bMap.findEmpty();
            int size = node.getSize();

            int index = bMap.emptySlots.remove(0);
            this.bMap.makeOne(index);
            node.setStart(index);
            int prev = index;
            size--;

            while(size!=0){
                index = bMap.emptySlots.remove(0);
                this.bMap.makeOne(index);
                fileAllocAr[prev] = index;
                prev = index;
                size--;
            }
            fileAllocAr[prev] = -1;
            iNodeList.add(node);
            this.totalSize -= node.getSize();
            return true;
        }else return false;
    }
    public String toString(){
        String str = "";
        int val = 0;
        for(int i = 0; i< iNodeList.size(); i++){
            str += iNodeList.get(i);
            int index = iNodeList.get(i).getStart();
            val = fileAllocAr[index];
            while(val != -1){
                str += val;
                val = fileAllocAr[val];
                if(val != -1)
                    str += "->";
            }
            str += "\n";
        }
        return str;
    }
    public int findFile (String name){
        if(totalSize == 64){
            return -1;
        }
        int index =0;
        while(!name.equals(iNodeList.get(index).getName())){
            index++;

            if(index >= iNodeList.size() )
                return -1;
        }
        return index;
    }
    public void printFS(){
        System.out.println(this.toString());
    }
}
