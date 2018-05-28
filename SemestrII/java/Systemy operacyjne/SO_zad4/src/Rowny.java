
public class Rowny {
    private Proces[] procesy;   //tablica procesów
    private int ramki;          //ilość ramek
    private int strony;         //ilość stron
    private int bledy = 0;      //błędy

    //konstruktor do skopiowania procesów i ilości ramek
    public Rowny(Proces[] zewProcesy, int zewRamki) {
        procesy = zewProcesy;
        ramki = zewRamki;

        //ustalamy łączną ilość stron
        for (int i = 0; i < procesy.length; i++)
            strony += procesy[i].getIloscStron();

        //ustalamy ilość ramek dla każdego procesu
        int stalaIlosc = ramki / procesy.length;

        //i je przypisujemy
        for (int i = 0; i < procesy.length; i++) {
            procesy[i].setRamki(stalaIlosc);
            ramki -= stalaIlosc;
        }

        //jeśli zostały wolne ramki dajemy każdemu po kolei po ramce
        if (ramki > 0) {
            int i = 0;
            while (i < procesy.length && ramki != 0) {
                int ramkiProcesu = procesy[i].getRamki();
                ramkiProcesu++;
                procesy[i].setRamki(ramkiProcesu);
                ramki--;
                i++;
            }
        }
        //wyświetlam ilość ramek
        for (int i = 0; i < procesy.length; i++) {
            System.out.println(procesy[i].getRamki());
        }
        System.out.println();
        int suma = 0;
        for (int i = 0; i < procesy.length; i++) {
            suma += procesy[i].getRamki();
        }
        System.out.println(suma);


    }

    //włącza symulacje
    public void uruchom() {
        for (int i = 0; i < procesy.length; i++) {
            procesy[i].symuluj();
            bledy += procesy[i].getBledy();
        }
    }


    //**********METODY POMOCNICZE**********
    //zwraca ilość błędów w programie
    public int getBledy() {
        return bledy;
    }
}
