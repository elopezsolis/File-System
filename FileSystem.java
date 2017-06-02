import java.util.ArrayList;

/**
 * This class Implements a file System
 */
public class FileSystem {
    int[] fileAllocAr;
    ArrayList<iNode> iNodeList;
    Bitmap bMap;
    int totalSize;

    public static void main(String[] args){
        FileSystem fs = new FileSystem();
        fs.insert(new iNode("name1",2));
        fs.insert(new iNode("name2",2));
        fs.insert(new iNode("name3",60));
        fs.printFS();
        fs.bMap.printBMap();
        System.out.println(fs.bMap.emptySlots);
        System.out.println(fs.totalSize);
        fs.delete("name3");
        fs.printFS();
        fs.bMap.printBMap();
        fs.bMap.findEmpty();
        System.out.println(fs.bMap.emptySlots);
        System.out.println(fs.totalSize);


    }
    public FileSystem(){
        totalSize = 64;
        fileAllocAr = new int[totalSize];
        iNodeList = new ArrayList();
        bMap = new Bitmap();
        for(int i=0;i<fileAllocAr.length;i++){
            fileAllocAr[i] = -2;
        }
    }
    public boolean delete(String name){
        int index =0;
        while(!name.equals(iNodeList.get(index).getName())){
            index++;
            if(index >= iNodeList.size())
                return false;
        }

        iNode temp = this.iNodeList.get(index);
        int num = temp.getStart();
        int contents = fileAllocAr[num];
        this.bMap.makeZero(num);

        num = contents;

        //Not depend on size b 
        while(contents !=-1){
            contents = fileAllocAr[num];
            this.bMap.makeZero(num);
            num = contents;
        }
        this.totalSize +=temp.getSize();
        this.iNodeList.remove(index);

        return true;

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
    public void printFS(){
        System.out.println(this.toString());
    }
}
