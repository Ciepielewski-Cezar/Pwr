public class Urzednik extends Pracownik{
    private double podstawa;
    private double premia;
    
    public Urzednik(String nazwisko, double etat, double podstawa, double premia){
        super(nazwisko, etat);
        this.podstawa = podstawa;
        this.premia = premia;
    }
    
    public double getWyplata(){
        double wyplata = etat * podstawa + premia;
        return wyplata;
    }
    
    @Override
    public String toString(){
        return getNazwisko() + " urzędnik " + getEtat();
    }
}