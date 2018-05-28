import java.util.Random;

public class Zadanie {
    private double obciazenie;  //obciążenie procesora danym procesem
    private int trwanie;        // czas trwania określonego zadania
    private int realneTrwanie;
    private int nadejscie;      // czas nadejścia zadania
    private int czasOczekiwania = 0;
    private boolean czyZrobiony;

    public Zadanie(){
        //PARAMETRY GENEROWANIA ZADAŃ
        double minObciazenie = 0.01;            //minimalne obciążenie zadania
        double maxObciazenie = 4;               //maksymalne obciązenie jakie może mieć zadanie
        int maxTrwanie = 50;                    //maksymalny czas trwania zadania
        int maxNadejscie = 2000;                //maksymalny czas nadejścia zadania

        Random rand = new Random();
        double obc  = minObciazenie + (maxObciazenie - minObciazenie) * rand.nextDouble();
        obciazenie = Math.floor(obc * 100) / 100;   //formatowanie obiążenia do formatu 0.00

        trwanie = (int) (Math.random()*maxTrwanie)+1;
        realneTrwanie = 0;
        czyZrobiony = false;

        nadejscie = (int) (Math.random()*maxNadejscie)+1;
    }

    public Zadanie(Zadanie zad) {
        this.obciazenie = zad.getObciazenie();
        this.trwanie = zad.getTrwanie();
        this.realneTrwanie = zad.getRealneTrwanie();
        this.nadejscie = zad.getNadejscie();
        this.czasOczekiwania = zad.getRealneTrwanie();
        this.czyZrobiony = zad.isCzyZrobiony();
    }


    //*************** METODY POMOCNICZE ***************

    @Override
    public String toString() {
        return "Zadanie{" +
                "obciazenie=" + obciazenie +
                ", trwanie=" + trwanie +
                ", realneTrwanie=" + realneTrwanie +
                ", nadejscie=" + nadejscie +
                ", czasOczekiwania=" + czasOczekiwania +
                ", czyZrobiony=" + czyZrobiony +
                '}';
    }


    //*************** GETTERY I SETTERY ***************

    public double getObciazenie() {
        return obciazenie;
    }

    public void setObciazenie(double obciazenie) {
        this.obciazenie = obciazenie;
    }

    public int getTrwanie() {
        return trwanie;
    }

    public void setTrwanie(int trwanie) {
        this.trwanie = trwanie;
    }

    public int getNadejscie() {
        return nadejscie;
    }

    public void setNadejscie(int nadejscie) {
        this.nadejscie = nadejscie;
    }

    public int getRealneTrwanie() {
        return realneTrwanie;
    }

    public void setRealneTrwanie(int realneTrwanie) {
        this.realneTrwanie = realneTrwanie;
    }

    public int getCzasOczekiwania() {
        return czasOczekiwania;
    }

    public void setCzasOczekiwania(int czasOczekiwania) {
        this.czasOczekiwania = czasOczekiwania;
    }

    public boolean isCzyZrobiony() {
        return czyZrobiony;
    }

    public void setCzyZrobiony(boolean czyZrobiony) {
        this.czyZrobiony = czyZrobiony;
    }
}
