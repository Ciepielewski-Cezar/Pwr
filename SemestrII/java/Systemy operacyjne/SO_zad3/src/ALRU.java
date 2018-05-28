/**
 * Created by Czarek on 02.05.2017.
 */
public class ALRU {
    //dane skopiowane z klasy dane
    long ilosc;
    private int[] ramki;
    private int strony;
    private int[] ciag;
    private int bledy = 0;  // liczba błędów stron

    public ALRU(Dane zew) {
        ramki = new int[zew.ramki.length];
        ciag = new int[zew.ciag.length];
        ilosc = zew.getSize();
        strony = zew.getStrony();
        for (int i = 0; i < zew.ramki.length; i++)
            this.ramki[i] = zew.ramki[i];
        for (int i = 0; i < zew.ciag.length; i++)
            this.ciag[i] = zew.ciag[i];
    }

    //główna symulacja algorytm drugiej szansy
    public int symulacja() {
        int wskaznik = 0;                    //wskazuje na kolejne ramki
        int[] bity = new int[ramki.length];  //tablica która wskazuje bit strony w danej ramce
        wyjedynkuj(bity);                    //na początek wszystkie bity mają bit = 1
        boolean czyDodany = false;           //do pętli która zmniejsza bity aż doda się strona do ramki

        for (int i = 0; i < ciag.length; i++) {
            czyDodany = false;

            if (!czyWRamce(ciag[i])) {
                while (!czyDodany) {
                    if (bity[wskaznik] == 1)
                        bity[wskaznik++] = 0;
                    else {
                        bity[wskaznik] = 1;
                        ramki[wskaznik++] = ciag[i];
                        bledy++;
                        czyDodany = true;
                    }
                    if (wskaznik == ramki.length)
                        wskaznik = 0;
                }

            } else
                bity[gdzie(ciag[i])] = 1;
        }
        return bledy;
    }

    //**********METODY POMOCNICZE**********

    //zwraca ramke z szukaną stroną
    public int gdzie(int strona) {
        int gdzie = -1;

        for (int i = 0; i < ramki.length; i++) {
            if (ramki[i] == strona)
                gdzie = i;
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

    //daje jedynki na wszystkich pozycjach
    public void wyjedynkuj(int[] tab) {
        for (int i = 0; i < tab.length; i++)
            tab[i] = 1;
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