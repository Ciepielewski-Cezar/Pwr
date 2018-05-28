public class Robotnik extends Pracownik{
    private double przepGodz;
    private double stawkaGodz;
    
    final static double LIMIT = 160;
    final static double BONUS = 0.5;
    
    public Robotnik(String nazwisko, double etat, double przepGodz, double stawkaGodz, double nadgodziny){
        super(nazwisko, etat);
        this.przepGodz = przepGodz;
        this.stawkaGodz = stawkaGodz;
    }
    
    public double getWyplata(){
        double wyplata;
        if (przepGodz < LIMIT){
            wyplata = przepGodz * stawkaGodz;
        }
        else{
            wyplata = przepGodz * stawkaGodz + (stawkaGodz-LIMIT)*BONUS;
        }
        return wyplata;
    }
    
    @Override
    public String toString(){
        return getNazwisko() + " Robotnik " + getEtat();
    }
}