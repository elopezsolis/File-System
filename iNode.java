/**
 * Class iNode represents a file
 * this file contains
 * - a name
 * - starting block:
 * - size
 *
 */
public class iNode {
    private String name;
    private int start;
    private int size;

    public iNode(String name,int size){
        this.name = name;
        this.size = size;
        this.start = -2; //- the number -2 will be used as dummy value
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    @Override
    public String toString(){
        return this.name + " " + this.start + "->";
    }
}
