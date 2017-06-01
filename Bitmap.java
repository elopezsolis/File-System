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
        Bitmap map = new Bitmap();
        map.bitMap = 1048575;
        map.findEmtpy();
    }

    /**
     * Function makes the passed bit number a 1. Bits start from 0-63;
     * If bit is already 1 then it doesn't do anything
     * @param bitNumber - bit number that will be made 1.
     */
    public void makeOne(int bitNumber){
        long temp = (long)Math.pow(2,bitNumber);
        bitMap = bitMap | temp;
        System.out.println("bitMap " + bitMap);
    }
    public void makeZero(int bitNumber){
        long temp =  (long)Math.pow(2,bitNumber);
        bitMap = bitMap ^ temp;
        System.out.println("xor" + bitMap);
    }
    public void findEmtpy(){
        long current = bitMap;
        long newNum = 1;
        int count =0;
        emptySlots = new ArrayList<>();
        while(count <64) {
            System.out.print("new" + newNum);
            long temp = (current | newNum);
            if(current != temp) {
                emptySlots.add(count);
                current = temp;
            }
            newNum = (newNum << 1) +1;
            System.out.println(" count "+ count + "temp " + temp  );
            count++;
        }
    }
}
