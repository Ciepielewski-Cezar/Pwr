
public class ZlozonyComparator  implements Comparator{
    public static final ZlozonyComparator INSTANCE = new ZlozonyComparator();
    
    private ZlozonyComparator() {} 
    
    public int compare(Object left, Object right) throws ClassCastException{
        int i = ((Samochod)left).porRok((Samochod)right);
        if(i == 0){
            i = ((Samochod)left).porMarka((Samochod)right);
                if(i == 0){
                   i = ((Samochod)left).porKolor((Samochod)right);
                }
        }
        return i;
    }
}
