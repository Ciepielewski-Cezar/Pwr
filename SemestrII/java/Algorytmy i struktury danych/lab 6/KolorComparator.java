
public class KolorComparator implements Comparator{
    public static final KolorComparator INSTANCE = new KolorComparator();
    
    private KolorComparator(){}
    
    public int compare(Object left, Object right) throws ClassCastException{
        return ((Samochod)left).porKolor((Samochod)right);
    }
}
