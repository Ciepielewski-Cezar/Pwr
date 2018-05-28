/**
 * Created by Czarek on 02.05.2017.
 */
public class OPT {
    //dane skopiowane z klasy dane
    long ilosc;
    private int[] ramki;
    private int strony;
    private int[] ciag;
    private int bledy = 0;  // liczba błędów stron


    public OPT(Dane zew) {
        ramki = new int[zew.ramki.length];
        ciag = new int[zew.ciag.length];
        ilosc = zew.getSize();
        strony = zew.getStrony();
        for (int i = 0; i < zew.ramki.length; i++)
            this.ramki[i] = zew.ramki[i];
        for (int i = 0; i < zew.ciag.length; i++)
            this.ciag[i] = zew.ciag[i];
    }

    //główna symulacja
    public int symulacja() {
        for (int i = 0; i < ciag.length; i++) {
            if (!czyWRamce(ciag[i])) {
                ramki[nieuzywana(i)] = ciag[i];
                bledy++;
            }

        }
        return bledy;
    }


    //**********METODY POMOCNICZE**********

    //zwraca ramkę której liczba będzie najdłużej nieużywana
    private int nieuzywana(int poczatek) {
        int[] tablica = new int[ramki.length];  // tablica do zliczania poszczególnych ramek
        wyzeruj(tablica);   //zerujemy bo mogą być syfy
        boolean trafiony = false;
        int max = Integer.MIN_VALUE;
        int gdzie = -1;

        for (int i = 0; i < ramki.length; i++) {
            for (int j = poczatek; j < ciag.length && !trafiony; j++) {
                if (ramki[i] == ciag[j])
                    trafiony = true;
                else
                    tablica[i]++;
            }
            trafiony = false;
        }
        for (int i = 0; i < tablica.length; i++) {
            if (tablica[i] > max) {
                max = tablica[i];
                gdzie = i;
            }

        }
        return gdzie;
    }


    //zwraca czy strona z ciągu jest już w ramce
    private boolean czyWRamce(int strona) {
        boolean czyWRamce = false;

        for (int i = 0; i < ramki.length; i++) {
            if (ramki[i] == strona) {
                czyWRamce = true;
            }
        }
        return czyWRamce;
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

}


