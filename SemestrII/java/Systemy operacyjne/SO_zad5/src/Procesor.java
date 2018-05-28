import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

public class Procesor {
    private List<Zadanie> sekwencja;         //lista z zadaniami
    private List<Zadanie> wykonywane;        //lista z zadaniami aktywnymi
    private LinkedList<Zadanie> oczekujace;  //lista dla zadań oczekujących
    private List<Zadanie> zrobione;          //lista wykonanych zadań do obliczania oczekujących i testowania

    private int mozliweZapytania;       //pozostałe możliwe zapytania
    private int indeksProcesora;
    private int liczbaZadan;
    int progMin;              //minimalny próg procesora
    int progMax;              //maksymalny próg procesora

    //***** do statstyk *****
    private int licznikZapytan = 0;
    private int licznikOdrzucone = 0;
    private int przeslaneZadania = 0;
    private int pytaniaObciazenie = 0;


    private int maxKolejka = 0;
    private double srCzasKolejka = 0;

    private int licznikKolejka = 0;
    private int iloscKolejka = 0;
    private double srIloscKolejka = 0;


    private double odchylenie = 0;
    private List<Double> odchylenieLista;

    private double obciazenie = 0;
    private double srObciazenie = 0;

    private int licznik = 0;



    public Procesor(double[] max, double[] min, int zap, int lZadan, int indeks) {
        sekwencja = new ArrayList<>();
        wykonywane = new ArrayList<>();
        oczekujace = new LinkedList<>();
        zrobione = new ArrayList<>(getLiczbaZadan());
        odchylenieLista = new ArrayList<>();

        mozliweZapytania = zap;
        liczbaZadan = lZadan;
        indeksProcesora = indeks;

        Random rand = new Random();
        double minimum = (min[0] + (min[1] - min[0]) * rand.nextDouble())*100;
         progMin = (int)Math.floor(minimum * 100) / 100;
        double maximum = (max[0] + (max[1] - max[0]) * rand.nextDouble())*100;
        progMax = (int) Math.floor(maximum * 100) / 100;


        for (int i = 0; i < liczbaZadan; i++) {
            Zadanie zad = new Zadanie();
            sekwencja.add(zad);
        }

        sortuj();   //sortowanie zadań od najwcześniejszego nadejścia
    }



    public Procesor(Procesor procesor) {
        sekwencja = new ArrayList<>();
        wykonywane = new ArrayList<>();
        oczekujace = new LinkedList<>();
        zrobione = new ArrayList<>(getLiczbaZadan());
        odchylenieLista = new ArrayList<>();

        this.mozliweZapytania = procesor.getMozliweZapytania();
        this.indeksProcesora = procesor.getIndeksProcesora();
        this.liczbaZadan = procesor.getLiczbaZadan();
        this.progMin = procesor.getProgMin();
        this.progMax = procesor.getProgMax();
        this.licznikZapytan = 0;
        this.licznikOdrzucone =  0;
        this.przeslaneZadania =  0;
        this.pytaniaObciazenie =  0;
        this.iloscKolejka =  0;
        this.maxKolejka =  0;
        this.srCzasKolejka =  0;
        this.odchylenie =  0;
        this.obciazenie =  0;
        this.srObciazenie = 0;
        this.licznik = 0;

        for (Zadanie z: procesor.getSekwencja()
                ) {
            this.sekwencja.add(new Zadanie(z));
        }
        sortuj();
    }

    //*************** METODY POMOCNICZE ***************
    private void sortuj() {
        for (int i = 0; i < sekwencja.size() - 1; i++) {
            for (int j = i + 1; j < sekwencja.size(); j++) {
                if (sekwencja.get(i).getNadejscie() > sekwencja.get(j).getNadejscie()) {
                    Zadanie temp = sekwencja.get(i);
                    sekwencja.set(i, sekwencja.get(j));
                    sekwencja.set(j, temp);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Procesor[" + indeksProcesora + "]{" +
                "licznikZapytan=" + licznikZapytan +
                ", srObciazenie=" + srObciazenie +
                ", odchylenie=" + odchylenie +
                ", przeslaneZadania=" + przeslaneZadania +
                ", pytaniaObciazenie=" + pytaniaObciazenie +
                ", iloscKolejka=" + iloscKolejka +
                ", maxKolejka=" + maxKolejka +
                '}';
    }

    //*************** GETTERY I SETTERY ***************


    public double getSrIloscKolejka() {
        return srIloscKolejka;
    }

    public void setSrIloscKolejka(double srIloscKolejka) {
        this.srIloscKolejka = srIloscKolejka;
    }

    public int getLicznikKolejka() {
        return licznikKolejka;
    }

    public void setLicznikKolejka(int licznikKolejka) {
        this.licznikKolejka = licznikKolejka;
    }

    public int getLicznikOdrzucone() {
        return licznikOdrzucone;
    }

    public void setLicznikOdrzucone(int licznikOdrzucone) {
        this.licznikOdrzucone = licznikOdrzucone;
    }

    public double getSrCzasKolejka() {
        return srCzasKolejka;
    }

    public void setSrCzasKolejka(double srCzasKolejka) {
        this.srCzasKolejka = srCzasKolejka;
    }

    public List<Double> getOdchylenieLista() {
        return odchylenieLista;
    }

    public void setOdchylenieLista(List<Double> odchylenieLista) {
        this.odchylenieLista = odchylenieLista;
    }

    public List<Zadanie> getSekwencja() {
        return sekwencja;
    }

    public void setSekwencja(List<Zadanie> sekwencja) {
        this.sekwencja = sekwencja;
    }

    public LinkedList<Zadanie> getOczekujace() {
        return oczekujace;
    }

    public void setOczekujace(LinkedList<Zadanie> oczekujace) {
        this.oczekujace = oczekujace;
    }

    public List<Zadanie> getWykonywane() {
        return wykonywane;
    }

    public void setWykonywane(List<Zadanie> wykonywane) {
        this.wykonywane = wykonywane;
    }

    public double getSrObciazenie() {
        return srObciazenie;
    }

    public void setSrObciazenie(double srObciazenie) {
        this.srObciazenie = srObciazenie;
    }

    public int getLicznik() {
        return licznik;
    }

    public void setLicznik(int licznik) {
        this.licznik = licznik;
    }

    public int getMozliweZapytania() {
        return mozliweZapytania;
    }

    public void setMozliweZapytania(int mozliweZapytania) {
        this.mozliweZapytania = mozliweZapytania;
    }

    public int getLiczbaZadan() {
        return liczbaZadan;
    }

    public void setLiczbaZadan(int liczbaZadan) {
        this.liczbaZadan = liczbaZadan;
    }

    public int getProgMin() {
        return progMin;
    }

    public void setProgMin(int progMin) {
        this.progMin = progMin;
    }

    public int getProgMax() {
        return progMax;
    }

    public void setProgMax(int progMax) {
        this.progMax = progMax;
    }

    public int getLicznikZapytan() {
        return licznikZapytan;
    }

    public void setLicznikZapytan(int licznikZapytan) {
        this.licznikZapytan = licznikZapytan;
    }

    public double getObiazenie() {
        return obciazenie;
    }

    public void setObiazenie(double obiazenie) {
        this.obciazenie = obiazenie;
    }

    public double getOdchylenie() {
        return odchylenie;
    }

    public void setOdchylenie(double odchylenie) {
        this.odchylenie = odchylenie;
    }

    public int getPrzeslaneZadania() {
        return przeslaneZadania;
    }

    public void setPrzeslaneZadania(int przeslaneZadania) {
        this.przeslaneZadania = przeslaneZadania;
    }

    public int getPytaniaObciazenie() {
        return pytaniaObciazenie;
    }

    public void setPytaniaObciazenie(int pytaniaObciazenie) {
        this.pytaniaObciazenie = pytaniaObciazenie;
    }

    public double getObciazenie() {
        return obciazenie;
    }

    public void setObciazenie(double obciazenie) {
        this.obciazenie = obciazenie;
    }

    public int getIndeksProcesora() {
        return indeksProcesora;
    }

    public void setIndeksProcesora(int indeksProcesora) {
        this.indeksProcesora = indeksProcesora;
    }

    public int getIloscKolejka() {
        return iloscKolejka;
    }

    public int getMaxKolejka() {
        return maxKolejka;
    }

    public void setMaxKolejka(int maxKolejka) {
        this.maxKolejka = maxKolejka;
    }

    public void setIloscKolejka(int iloscKolejka) {


        this.iloscKolejka = iloscKolejka;
    }

    public List<Zadanie> getZrobione() {
        return zrobione;
    }

    public void setZrobione(List<Zadanie> zrobione) {
        this.zrobione = zrobione;
    }
}
