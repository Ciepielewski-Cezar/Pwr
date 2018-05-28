import java.util.ArrayList;

public class LRU {
    private int ramki;      //ilość ramek
    private int[] ciag;     //ciąg odwołań
    private ArrayList<Integer> pamiec;  //nasza pamięć
    private int bledy = 0;  // liczba błędów stron


    //konstruktor kopiujący ciąg odwołań i ilość ramek
    public LRU(int[] ciagZew, int iloscRamek) {
        ramki = iloscRamek;
        pamiec = new ArrayList<>(iloscRamek);
        ciag = new int[ciagZew.length];

        for (int i = 0; i < ciagZew.length; i++) {
            ciag[i] = ciagZew[i];
        }
    }


    //główna symulacja
    public void symulacja() {
        for (int i = 0; i < ciag.length; i++) {
            if (!czyWRamce(ciag[i]) && pamiec.size() < ramki) {
                bledy++;
                pamiec.add(ciag[i]);

            } else if (!czyWRamce(ciag[i]) && pamiec.size() == ramki) {
                int[] uzywane = new int[ramki];

                for (int j = i - 1; j >= 0; j--) {
                    int strona = znajdzStrone(ciag[i]);
                    if (!(strona < 0)) {
                        uzywane[strona]++;
                    }
                }
                if(pamiec.size() >0){
                pamiec.remove(namniejszy(uzywane));
                pamiec.add(ciag[i]);
                bledy++;}
            }
        }
    }


    //**********METODY POMOCNICZE**********

    //szuka indeks strony w pamięci i ją zwraca
    private int znajdzStrone(int strona) {
        int indeks = Integer.MIN_VALUE;

        for (int i = 0; i < ramki; i++) {
            if (pamiec.get(i) == strona) {
                indeks = i;
                break;
            }
        }
        return indeks;
    }

    //zwraca czy strona z ciągu jest już w ramce
    private boolean czyWRamce(int strona) {
        return pamiec.contains(strona);
    }

    //zwraca indeks najmniejszego elementu w tablicy
    private int namniejszy(int[] tab) {
        int najmniejszy = 0;

        for (int i = 1; i < tab.length; i++) {
            if (tab[i] < tab[najmniejszy]) {
                najmniejszy = i;
            }
        }
        return najmniejszy;
    }


    public int getBledy() {
        return bledy;
    }

    //zeruje podaną tablicę
    public void wyzeruj(int[] tab) {
        for (int i = 0; i < tab.length; i++)
            tab[i] = 0;
    }

    //daje maksymalną wartość w tablicy
    public void wymaksuj(int[] tab) {
        for (int i = 0; i < tab.length; i++)
            tab[i] = Integer.MAX_VALUE;
    }

    //wyświetla podaną tablicę
    public void wyswietl(int[] tab) {
        for (int i = 0; i < tab.length; i++)
            System.out.println(tab[i]);
    }

    //wyświetla ciąg
    public void wyswietlCiag() {
        for (int i = 0; i < ciag.length; i++) {
            System.out.println(i + ": " + ciag[i]);
        }
    }

    /*
    public void symulacja() {
        for (int i = 0; i < ciag.length; i++) {
            if (!czyWRamce(ciag[i]) && pamiec.size() < ramki) {
                bledy++;
                pamiec.add(ciag[i]);
            } else if (!czyWRamce(ciag[i]) && pamiec.size() == ramki) {
                int[] uzywane = new int[ramki];
                for (int j = i - 1; j >= 0; j--) {
                    int strona = znajdzStrone(ciag[i]);
                    if (!(strona < 0)) {
                        uzywane[strona]++;
                    }
                }
                if(pamiec.size() >0){
                pamiec.remove(namniejszy(uzywane));
                pamiec.add(ciag[i]);
                bledy++;}
            }
        }
    }
     */
}