

public class Wierzcholek{
    private int wartosc;
    private boolean czyOdwiedzony;
    private String kolor;
    
    public Wierzcholek(int w){
        wartosc = w;
        czyOdwiedzony = false;
        kolor = "bialy";
    }
    
    public int getWartosc(){
        return wartosc;
    }
    
    public void serWartosc(int w){
        wartosc = w;
    }
    
    public boolean getCzyOdwiedzony(){
        return czyOdwiedzony;
    }
    
    public String getKolor(){
        return kolor;
    }
}
    