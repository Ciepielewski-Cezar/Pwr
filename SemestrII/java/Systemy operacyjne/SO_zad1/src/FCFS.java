import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FCFS {
    Cykl wew = new Cykl(limit);
    private ArrayList<Proces> lista = new ArrayList<>();
    private static int limit;  //ilość procesów w algorytmie
    int[] czasy = new int[limit];   //do przechowywania czasu oczekiwania procesów
    private int czasAlgorytmu = 0;  //średni czas całego algorytmu
    private int suma = 0;  //suma długości procesów w algorytmie


    public FCFS(Cykl zew) {
        limit = zew.getLimit();
        for(Proces p : zew.cykl){
            lista.add(new Proces(p));
        }
    }
    // Algorytm nie wykorzystuje pól procesu (realnaDP, wykonany). Są one potrzebne jako pomocnicze do innych algorytmów
    int symulacja() {
        wyrownaj();
        wyswietl();
        int maxWejscie = lista.get(lista.size() - 1).getCzasWstawienia();

        //System.out.println(maxWejscie);
        System.out.println();


        //Główne działanie programu
        for (int zmienna = 0; zmienna <= maxWejscie; zmienna++) {
            for (Proces p : lista) {
                if (zmienna == p.getCzasWstawienia()) {
                    if (suma - p.getCzasWstawienia() >= 0) {
                        p.setCzasOczekiwania(suma - p.getCzasWstawienia());

                    } else {
                        p.setCzasOczekiwania(0);
                    }
                    suma += p.getDlugoscProcesu();
                }
            }
        }
        long s = 0; //zlicza dugość oczekiwania
        for (Proces p : lista)
            s += p.getCzasOczekiwania();
        czasAlgorytmu = (int) s / limit;

        //wyswietl();
        System.out.print("Czas symulacji FCFS: ");

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