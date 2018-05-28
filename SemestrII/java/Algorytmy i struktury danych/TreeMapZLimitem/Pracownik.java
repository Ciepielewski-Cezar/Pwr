public abstract class Pracownik implements Comparable<Pracownik>{
    private String nazwisko;
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

    public int compareTo(Pracownik prac){
        return nazwisko.compareTo(prac.nazwisko);
    }

    public String toString(){
        return "nazwisko: " + nazwisko + " etat: " + etat;
    }

    public abstract double getWyplata();
}