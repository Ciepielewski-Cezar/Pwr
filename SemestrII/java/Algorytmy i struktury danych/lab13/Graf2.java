import java.util.ArrayList;

public class Graf2{
    ArrayList<Integer>[] ls;
    private int krawedzie;
    private int wierzcholki;
    
    public Graf2(int wierzcholki){
        this.wierzcholki = wierzcholki;
        krawedzie = 0;
        ls = new ArrayList[wierzcholki];
        
        for (int i = 0; i < wierzcholki; i++) {
            ls[i] = new ArrayList<Integer>();
        }
    }
    
    public void addKrawedz(int a, int b){
        ls[a].add(b);
        ls[b].add(a);
        krawedzie++;
    }
    
    
    public int getKrawedzie(){
        return krawedzie;
    }
    
    public int getWierzcholki(){
        return wierzcholki;
    }
}
