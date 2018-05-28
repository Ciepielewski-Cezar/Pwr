/**
 * Created by Czarek on 02.05.2017.
 */
public class LRU {
    //dane skopiowane z klasy dane
    long ilosc;
    private int[] ramki;
    private int strony;
    private int[] ciag;
    private int bledy = 0;  // liczba błędów stron


    public LRU(Dane zew) {
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
                bledy++;
                ramki[0] = ciag[i];
                naStos(ciag[i]);
            } else
                naStos(ciag[i]);
        }
        return bledy;
    }

    //**********METODY POMOCNICZE**********

    private void naStos(int strona) {
        for (int i = 0; i < ramki.length; i++) {
            if (ramki[i] == strona) {
                int temp = ramki[i];

                for (int j = i + 1; j < ramki.length; j++)
                    ramki[j - 1] = ramki[j];
                ramki[ramki.length - 1] = temp;
            }

        }

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