import java.io.File;
import java.util.ArrayList;

/**
 * Created by Erick on 5/28/2017.
 */
public class FileSystem {

    public static void main(String[] args){
        FileSystem fs = new FileSystem();
        fs.insert(new iNode("file1",55));
        System.out.println(fs.totalSize);
        fs.insert(new iNode("file1",9));
        System.out.println("2" + fs.totalSize);
        fs.insert(new iNode("file1",1));
        System.out.println(" 3" + fs.toString());
    }
    int[] fileAllocAr;
    ArrayList<iNode> list;
    long bitMap;
    int totalSize;

    public FileSystem(){
        totalSize = 64;
        fileAllocAr = new int[totalSize];
        list = new ArrayList();
        bitMap = 0;
        for(int i=0;i<fileAllocAr.length;i++){
            fileAllocAr[i] = -2;
        }
    }

    /**
     * TODO: Problem when the loop reaches fileAllocAr.size()?
     * @param temp
     * @return
     */
    public boolean insert(iNode temp){
        //makes sure we will have space for the node
        if(temp.getSize() < totalSize){
            System.out.println("out");
            int index =0; //counter
            int size = temp.getSize();
            int prev =-1;

            while(size!=0){
                if(fileAllocAr[index]== -2){
                    if (temp.getStart() == -2) temp.setStart(index);
                    else fileAllocAr[prev] = index;
                    prev= index;
                    size--;
                }
                index ++;
            }
            fileAllocAr[prev] = -1;
            list.add(temp);
            this.totalSize -= temp.getSize();
            return true;
        }else return false;
    }
    public String toString(){
        String str = "";
        int val = 0;
        System.out.println(list.size());
        for(int i =0; i< list.size();i++){
            str += list.get(i);
            int index = list.get(i).getStart();
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

}
