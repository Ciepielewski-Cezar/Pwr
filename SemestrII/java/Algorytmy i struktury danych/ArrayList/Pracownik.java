public abstract class Pracownik{
    String nazwisko;
    double etat;
    
    public Pracownik(String nazwisko, double etat){
        this.nazwisko = nazwisko;
        this.etat = etat;
    }
    
    public String getNazwisko(){
        return nazwisko;
    }
    
    public double getEtat(){
        return etat;
    }
    
    public String toString(){
        return "nazwisko: " + nazwisko + " etat: " + etat;
    }
    
    public abstract double getWyplata();
}