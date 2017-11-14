import java.util.ArrayList;

/**
 * BitMap
 * Class tracks empty and allocated blocks in the file system where
 * a 0 indicates a free block and a 1 indicates an allocated block.
 * BitMap is managed by a 64 bit long.
 * Empty slots are stored in an ArrayList
 */
public class BitMap {
    long bitMap;
    ArrayList<Integer> emptySlots;
    public BitMap(){
        bitMap = 0;
        emptySlots = new ArrayList<>();
    }

    /**
     * Function makes the passed bit number a 1. Bits start from 0-63;
     * If bit is already 1 then it doesn't do anything
     * @param bitNumber - bit number that will be made 1.
     */
    public void makeOne(int bitNumber){
        long temp;
        if(bitNumber == 63)
            temp = ((long)Math.pow(2,bitNumber)* (-1)) -1;
        else
            temp = (long)Math.pow(2,bitNumber);
        bitMap = bitMap | temp;
    }

    /**
     * Makes the specified bit a 0
     * TODO: merge with makeOne.
     * @param bitNumber
     */
    public void makeZero(int bitNumber){
        long temp;
        if(bitNumber == 63)
            temp = ((long)Math.pow(2,bitNumber)* (-1)) -1;
        else
            temp = (long)Math.pow(2,bitNumber);
        bitMap = bitMap ^ temp;
    }

    /**
     * finds empty slots in bitMap
     */
    public void findEmpty(){
        long current = bitMap;
        long newNum = 1;
        int count =0;
        emptySlots = new ArrayList<>();
        while(count <64) {
            long temp = (current | newNum);
            if(current != temp) {
                emptySlots.add(count);
                current = temp;
            }
            newNum = (newNum << 1) +1;
            count++;
        }
    }
    public String toString(){
        long temp = this.bitMap;
        int count = 1;
        String str = String.format("%2d ",0);
        while(count <= 64){
            str = str + (temp & 0x1);
            temp = temp >>> 1;
            if(count %8 == 0 && count !=64)
                str+= "\n" + String.format("%2d ",count);
            count++;
        }
        return str;
    }
    public void printBMap(){
        System.out.println(this.toString());
    }

}
