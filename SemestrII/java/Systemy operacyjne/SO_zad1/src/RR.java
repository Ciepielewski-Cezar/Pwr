import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RR {
    Cykl wew = new Cykl(limit);
    private ArrayList<Proces> lista = new ArrayList<>();
    private static int limit;  //ilość procesów w algorytmie
    int[] czasy = new int[limit];   //do przechowywania czasu oczekiwania procesów
    private int czasAlgorytmu = 0;  //średni czas całego algorytmu
    private int suma = 0;  //suma długości procesów w algorytmie
    int kwantCzasu = 20;
    //int kwantCzasu = Integer.MAX_VALUE;


    public RR(Cykl zew) {
        limit = zew.getLimit();
        for(Proces p : zew.cykl){
            lista.add(new Proces(p));
        }
    }

    int symulacja() {
        wyrownaj();

        //wyswietl();
        //System.out.println("\n");

        int licznik = 0;
        int i = 0;
        long czas = 0;


        for (; licznik < limit;) {
            for (Proces x : lista) {
                if (!x.isWykonany() && czas >= x.getCzasWstawienia()) {
                    if(x.getDlugoscProcesu() - x.getRealnaDP() > kwantCzasu) {
                        x.setRealnaDP(x.getRealnaDP() + kwantCzasu);

                        if (suma - x.getCzasWstawienia() >= 0){
                            x.setCzasOczekiwania(x.getCzasOczekiwania() - x.getCzasWstawienia());
                        } else{
                            x.setCzasOczekiwania(0);
                        }
                        suma += kwantCzasu;
                        czas +=kwantCzasu;
                    }
                    else {
                        int doDodania = x.getDlugoscProcesu() - x.getRealnaDP();
                        x.setRealnaDP(x.getDlugoscProcesu());
                        if (suma - x.getCzasWstawienia() >= 0) {
                            x.setCzasOczekiwania(suma - x.getCzasWstawienia());
                        }else x.setCzasOczekiwania(0);
                        suma += doDodania;
                        czas += doDodania;
                        x.setWykonany(true);
                        licznik++;
                        //System.out.println(i + ": " + x); //wyświetlenie po kolei wyników
                        i++;

                    }
                }
                else czas++;
            }
        }
        long s = 0; //zlicza dugość oczekiwania
        for (Proces p : lista)
            s += p.getCzasOczekiwania();
        czasAlgorytmu = (int) s / limit;

        //wyswietl();
        //System.out.println("\n");
        System.out.print("Czas symulacji rr: ");

        return czasAlgorytmu;
    }




    private void dodajCykl(Cykl jakis) {
        wew.cykl = jakis.cykl;
        for (Proces r : wew.cykl){
            lista.add(r);
        }
    }

    private void wyswietl() {
        int i = 0;
        for (Proces p : lista) {
            System.out.println(i + ": " + p);
            i++;
        }
    }

    private void wyrownaj() {
        int wyr = lista.get(0).getCzasWstawienia();
        for (Proces p : lista) {
            p.setCzasWstawienia(p.getCzasWstawienia() - wyr);
        }
    }
/*
    private void sortuj() {
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).getCzasWstawienia() > lista.get(j).getCzasWstawienia()) {
                    Proces temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                }
            }
        }
    }
    */
}