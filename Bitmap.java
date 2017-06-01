import java.util.ArrayList;

/**
 * Manages the Bit Map, 1 represents an used space, 0 an empty one
 */
public class Bitmap {
    long bitMap;
    ArrayList<Integer> emptySlots;
    public Bitmap(){
        bitMap = 0;
        emptySlots = new ArrayList<>();
    }
    public static void main(String[] args){
        Bitmap bitMap = new Bitmap();
        bitMap.bitMap = 0;
        bitMap.makeOne(63);
        System.out.println( bitMap.toString());
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
    public void makeZero(int bitNumber){
        long temp =  (long)Math.pow(2,bitNumber);
        bitMap = bitMap ^ temp;
    }
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

}
