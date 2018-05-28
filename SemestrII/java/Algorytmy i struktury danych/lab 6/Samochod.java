
public class Samochod{
    private String marka;
    private String kolor;
    private int rok;
    
    public Samochod(String m, String k, int r){
        marka = m;
        kolor = k;
        rok = r;
    }
    
    public String getMarka() {return marka;}
    public String getKolor() {return kolor;}
    public int getRok() {return rok;}
    
    public void setMarkia(String m) {marka = m;}
    public void setKolor(String k){ kolor = k;}
    public void setRok(int r){ rok = r;}
    
    public String toString(){
        return "marka: " + marka + " kolor: " + kolor + " rok: " + rok;
    }
    
    public int porMarka(Samochod s){ return marka.compareTo(s.getMarka());}
    public int porKolor(Samochod s){ return kolor.compareTo(s.getKolor());}
    public int porRok (Samochod s){ return rok - s.getRok();}
    
}
    