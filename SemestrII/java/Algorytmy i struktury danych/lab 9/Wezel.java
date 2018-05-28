
public class Wezel<T> {
    int value;
    Wezel left;
    Wezel right;
    /*
    public Wezel(int value, Wezel left, Wezel right){
        this.value = value;
        this.left = left;
        this.right = right;
    }
    */
    public Wezel(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }
}