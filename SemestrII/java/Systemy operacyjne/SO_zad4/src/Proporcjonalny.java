
public class Proporcjonalny {
    private Proces[] procesy;   //tablica procesów
    private int ramki;          //ilość ramek
    private int strony;         //ilość stron
    private int bledy = 0;      //błędy

    //konstruktor do skopiowania procesów i ilości ramek
    public Proporcjonalny(Proces[] zewProcesy, int zewRamki) {
        procesy = zewProcesy;
        ramki = zewRamki;

        //ustalamy łączną ilość stron
        for (int i = 0; i < procesy.length; i++)
            strony += procesy[i].getIloscStron();

        //wskaźnik do ustalenia ilości ramek dla każdego procesu
        double wskaznik = (double) ramki / strony;
        int i = 0;

        //przypisujemy ilość ramek dla każdego procesu
        while (ramki > 0 && i < procesy.length) {
            int iloscRamek = (int) (wskaznik * procesy[i].getIloscStron());
            System.out.println(iloscRamek);
            procesy[i].setRamki(iloscRamek);
            ramki -= iloscRamek;
            i++;
        }
        //rozdzielamy resztę ramek
        if (ramki > 0 && i == procesy.length) {
            int indeks = najmniejszy();
            procesy[indeks].ramki = procesy[indeks].ramki + ramki;
        }
        System.out.println();
        int suma = 0;
        for (int k = 0; k < procesy.length; k++) {
            suma += procesy[k].getRamki();
        }
            System.out.println(suma);
        ramki = 0;
    }

    //włącza symulacje
    public void uruchom() {
        for (int i = 0; i < procesy.length; i++) {
            procesy[i].symuluj();
            bledy += procesy[i].getBledy();
        }
    }


    //**********METODY POMOCNICZE**********

    //proces z najmniejszą ilością ramek
    public int najmniejszy() {
        int indeks = 0;

        for (int i = 1; i < procesy.length; i++) {
            if (procesy[i].ramki < procesy[indeks].ramki && procesy[i].ramki < procesy[i].getIloscStron()) {
                indeks = i;
            }
        }
        return indeks;
    }

    //zwraca ilość błędów w programie
    public int getBledy() {
        return bledy;
    }
}
