public class Towar{
    private String nazwa;
    private double ilosc;
    private double cena;
    private double wartosc;
    
    public Towar(String n, double i, double c, double w){
        nazwa = n;
        ilosc = i;
        cena = c;
        wartosc = w;
    }
    
    public String getNazwa(){ return nazwa;}
    
    public double getWartosc(){return wartosc;}
    public void setWartosc(double c){ wartosc = c;}
    
    public double getIlosc(){return ilosc;}
    
    public double getCena(){return cena;}
    public void setCena(double c){ cena = c;}
    
    public String toString(){
        return nazwa+" "+ilosc+" "+cena+" "+wartosc;
    }
}