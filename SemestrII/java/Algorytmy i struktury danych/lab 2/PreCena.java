
public class PreCena implements Predicate{
    
    double g = 500;
    public boolean accept(Object o){
        return ((((Towar)o).getCena())>g);
    }
}
