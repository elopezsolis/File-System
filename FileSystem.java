import java.util.ArrayList;

/**
 * Created by Erick on 5/28/2017.
 */
public class FileSystem {
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

    public boolean insert(iNode temp){
        if(temp.getSize() < totalSize){
            int index =0; //counter
            int size = temp.getSize();
            int prev =-1;
            int next =-1;
            while(size!=0){
                if(fileAllocAr[index]== -2){
                    if(temp.getStart()==-2){
                        temp.setStart(index);
                    }else{

                    }

                }
                size--;
            }
        }
    }
}
