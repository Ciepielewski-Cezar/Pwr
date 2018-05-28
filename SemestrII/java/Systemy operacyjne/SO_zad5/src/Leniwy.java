import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class Leniwy {
    private Symulacja symulacjaWew;       //kopia symulacji do użytku wewnętrznego
    private int iloscZadan;

    //***** do statstyk *****
    private double srSrObciazenie = 0;
    private double srOdchylenie = 0;
    private int srPrzeslaneZadania = 0;
    private int srPytaniaObciazenie = 0;
    private int srLicznikZapytan = 0;
    private int srLicznikOdrzucone = 0;
    private int srSrIloscKolejka = 0;
    private int srMaxKolejka = 0;
    private int srSrCzasKolejka = 0;
    private  int czas = 0;


    public Leniwy(Symulacja symulacja) {
        symulacjaWew = new Symulacja(symulacja);
    }

    public void aktywuj() {
        iloscZadan = symulacjaWew.getLiczbaProcesorow() * symulacjaWew.procesory.get(0).getLiczbaZadan();       //oblicza ilość wszystkich zadań do wykonania. Zastosowanie w głównej pętli

        //symulacjaWew.wyswietlZadania();
        //System.out.println(symulacjaWew);


        while (iloscZadan != 0) {
            for (Procesor procesor : symulacjaWew.procesory) {                      //po wszystkich procesorach
                for (int i = 0; i < procesor.getSekwencja().size(); i++) {                   //po wszystkich zadaniach
                    Zadanie zadanie = procesor.getSekwencja().get(i);
                    //zrobić listę do usunięcia i potem usunąć
                    if (zadanie.getNadejscie() <= czas) {                           //jeśli zadanie isę pojawiło
                        przepiszZadanie(procesor.getIndeksProcesora(), zadanie);    //funkcja przepisująca zadanie

                    }
                }
            }
            czas++;                                                                 //dodajemy jednostkę czasu
            aktualizujZadania();                                                    //aktualizuje czas zadań i usuwa zrobione
            aktualizujOczekujace();                                                 //aktualizuje kolejke
            policzObciazenie();                                                     //dodaje obciązenie w danym momencie
            policzIloscKolejka();                                                   //liczy ilość zadan w kolejce
        }
        koncoweObciazenie();                                                        //dzieli obciążenie przez jednostki czasu dla każdego procesora
        sredniaKolejkaProcesora();                                                  //oblicza średni czas procesu w kolejce dla każdego procesora
        koncowaIloscKolejkaProcesora();                                             //oblicza średnią ilość zadań w kolejce
        odchylenieProcesora();                                                      //oblicza odchylenie dla każdego procesora
        srednieWartosci();                                                          //oblicza średnie wartości dla całego algorytmu

        //symulacjaWew.wyswietlZadania();
    }


    //*************** METODY POMOCNICZE ***************

    //funkcja przepisująca zadanie do innego procesora, jeśli wyczerpano limit zapytań i próg 100% dodaje do kolejki oczekującej
    //@param indeksProc: indeks bieżącego procesora
    //@param zadanie: zadanie do przepisania

    private boolean przepiszZadanie(int indeksProc, Zadanie zadanie) {
        boolean czyPrzepisano = false;                                                           //sprawdzamy czy przepisano zadanie
        boolean[] pomocnicza = new boolean[symulacjaWew.getLiczbaProcesorow()];                  //tablica pomocnicza do przepisywań bez powtórzeń

        Procesor biezacy = symulacjaWew.procesory.get(indeksProc);                               //podstawiamy bieżący procesor pod @param biezacy
        biezacy.setMozliweZapytania(0);                                                          //zerujemy licznik zapytań

        for (int i = 0; i < pomocnicza.length; i++) {
            pomocnicza[i] = false;                                                               //tablica false każdy indeks tablicy odpowiada procesorowi
        }

        pomocnicza[indeksProc] = true;                                                           //bieżący procesor będzie ignorowany gdy zostanie wylosowany


        //System.out.println("procesor: " + indeksProc);//do usunięcia

        while (biezacy.getMozliweZapytania() < symulacjaWew.getZapytania() && !czyPrzepisano) {  //sprawdzamy czy nie osiągneliśmy limitu zapytań i czy nie przepisano już gdzieś zadania
            int indPytany = (int) (Math.random() * symulacjaWew.getLiczbaProcesorow());          //losujemy liczbe z przedziału

            Procesor pytany = symulacjaWew.procesory.get(indPytany);                             //ta liczba jest procesorem w liście procesorów

            if (!pomocnicza[indPytany]) {                                                        //jeśli nie był pytany wcześniej
                if (pytany.getObciazenie() < pytany.getProgMax()) {                              //jeśli jego obciążenie nie zostało przekroczone

                    pytany.getWykonywane().add(zadanie);                                         //dodajemy pytanemu procesorowi zadanie do wykonywanych
                    biezacy.getSekwencja().remove(zadanie);                                      //i usuwamy z bieżącego procesora


                    pytany.setObciazenie(pytany.getObiazenie() + zadanie.getObciazenie());       //STATYSTYKA dodajemy obciążenie
                    biezacy.setPrzeslaneZadania(biezacy.getPrzeslaneZadania() + 1);              //STATYSTYKA inkrementacja przesłanych zadań
                    pytany.setPytaniaObciazenie(pytany.getPytaniaObciazenie() + 1);              //STATYSTYKA inkrementujemy licznik zadanych pytań(pytanemu)

                    czyPrzepisano = true;                                                        //potwierdzamy że przepisano zadanie

                } else {
                    biezacy.setLicznikOdrzucone(biezacy.getLicznikOdrzucone() + 1);              //STATYSTYKAjeśli odrzuci inkrementuj licznik odrzuconych
                }

                pomocnicza[indPytany] = true;                                                    //zaznaczamy że był już pytany
                biezacy.setLicznikZapytan(biezacy.getLicznikZapytan() + 1);                      //STATYSTYKA inkrementacja zapytań

            }

            biezacy.setMozliweZapytania(biezacy.getMozliweZapytania() + 1);                      //PĘTLA inkrementujemy możliwe zapytania

        }


        // jeśli wyczerpiemy ilość zapytań

        if (!czyPrzepisano) {                                                                    //sprawdzamy czy został osiągnięty limit na bieżacym procesorze
            if (biezacy.getObciazenie() + zadanie.getObciazenie() <= 100) {                      //jeśli obciążenie jest poniżej 100% to dodajemy do wykonywanych
                biezacy.getWykonywane().add(zadanie);
                biezacy.getSekwencja().remove(zadanie);

                biezacy.setObciazenie(biezacy.getObiazenie() + zadanie.getObciazenie());         //STATYSYKA dodajemy obciążenie
                biezacy.getOdchylenieLista().add(zadanie.getObciazenie());                       //STATYSTYKA lista do obliczania odchylenia

            } else {                                                                             //jeśli tak to dodajemy do oczekujących
                biezacy.getOczekujace().add(zadanie);
                biezacy.getSekwencja().remove(zadanie);


                biezacy.setIloscKolejka(biezacy.getIloscKolejka() + 1);                          //STATYSYKA ilość zadań w kolejce oczekującej
            }
        }

        return czyPrzepisano;                                                                    //zwracamy wartość czy przepisano do innego procesora
    }


    //funkcja aktualizująca czas zadań i usuwająca zrobione zadania

    private void aktualizujZadania() {
        for (Procesor procesor : symulacjaWew.procesory) {
            for (int i = 0; i < procesor.getWykonywane().size(); i++) {

                Zadanie zadanie = procesor.getWykonywane().get(i);

                zadanie.setRealneTrwanie(zadanie.getRealneTrwanie() + 1);                        //dodajemy do każdego zadania jednostkę czasu

                if (zadanie.getRealneTrwanie() == zadanie.getTrwanie()) {
                    zadanie.setCzyZrobiony(true);                                                //do testów, potwierdzenei wykonania

                    procesor.setObciazenie(procesor.getObiazenie() - zadanie.getObciazenie());   //STATYSTYKA odjęcie obicążenia

                    procesor.getZrobione().add(zadanie);                                         //dodaje zadanie do zrobionych
                    procesor.getWykonywane().remove(zadanie);                                    //usunięcie z listy wykonywanych
                    iloscZadan--;                                                                //GŁÓWNA PĘTLA WARUNEK
                }
            }
        }
    }


    //funkcja aktualizująca czas oczekiwania w kolejce oczekujących i dodające zadania do wykonywanych jeśli są warunki

    private void aktualizujOczekujace() {
        for (Procesor procesor : symulacjaWew.procesory) {
            for (int i = 0; i < procesor.getOczekujace().size(); i++) {
                Zadanie zadanie = procesor.getOczekujace().get(i);
                zadanie.setCzasOczekiwania(zadanie.getCzasOczekiwania() + 1);                   //dodajemy jednostkę czasu do oczekiwania

                if (procesor.getIloscKolejka() > procesor.getMaxKolejka())                      //STATYSYKA maksymalna ilość zadań w kolejce
                    procesor.setMaxKolejka(procesor.getIloscKolejka());

                if (procesor.getObciazenie() < procesor.getProgMax()) {                         //jeśli obciążenie spadło poniżej progu

                    Zadanie doPrzepisania = procesor.getOczekujace().removeFirst();             //usuwamy zadanie z listy oczekujących
                    procesor.getWykonywane().add(doPrzepisania);                                //i zapisujemy do wykonywanych
                    procesor.setObciazenie(procesor.getObiazenie() + doPrzepisania.getObciazenie());//zwiększamy obciążenie

                    procesor.setIloscKolejka(procesor.getIloscKolejka() - 1);                   //STATYSYKA ilość zadań w kolejce oczekującej

                }
            }
        }
    }


    //funkcja która dodaje obciążenie w każdej jednostce czasu

    private void policzObciazenie() {
        for (Procesor procesor : symulacjaWew.procesory) {
            procesor.setSrObciazenie(procesor.getSrObciazenie() + procesor.getObciazenie());
            procesor.setLicznik(procesor.getLicznik() + 1);
        }
    }

    private void policzIloscKolejka() {
        for (Procesor procesor : symulacjaWew.procesory) {
            if (procesor.getOczekujace() != null && procesor.getOczekujace().size() != 0) {
                procesor.setSrIloscKolejka(procesor.getSrIloscKolejka() + procesor.getIloscKolejka());
                procesor.setLicznikKolejka(procesor.getLicznikKolejka() + 1);
            }
        }
    }

    private void koncowaIloscKolejkaProcesora() {
        for (Procesor procesor : symulacjaWew.procesory) {
            procesor.setSrIloscKolejka(procesor.getSrIloscKolejka() / procesor.getLicznikKolejka());
        }
    }


    //funkcja która liczy średnie obciążenie dla każdego procesora
    private void koncoweObciazenie() {
        for (Procesor procesor : symulacjaWew.procesory) {
            procesor.setSrObciazenie(procesor.getSrObciazenie() / procesor.getLicznik());
        }
    }


    //funkcja liczy średni czas procesu w kolejce dla każdego procesora

    private void sredniaKolejkaProcesora() {
        for (Procesor procesor : symulacjaWew.procesory) {
            int licznik = 0;

            for (Zadanie zadanie : procesor.getZrobione()) {
                if (zadanie.getCzasOczekiwania() > 0) {
                    procesor.setSrCzasKolejka(procesor.getSrCzasKolejka() + zadanie.getCzasOczekiwania());
                    licznik++;

                }
            }
            procesor.setSrCzasKolejka(procesor.getSrCzasKolejka() / licznik);
        }
    }


    //funkcja liczy odchylenie standardowe dla procesora

    private void odchylenieProcesora() {
        for (Procesor procesor : symulacjaWew.procesory) {
            double suma = 0;

            for (Double liczba : procesor.getOdchylenieLista()) {
                double liczba1 = Math.pow((liczba + procesor.getSrObciazenie()), 2);
                suma += liczba1;
            }
            procesor.setOdchylenie(Math.sqrt(suma / procesor.getOdchylenieLista().size()));

        }
    }


    //funkcja która liczy średnie wartości statystyczne dla całego algorytmu

    private void srednieWartosci() {
        for (Procesor procesor : symulacjaWew.procesory) {
            srSrObciazenie += procesor.getSrObciazenie();
            srOdchylenie += procesor.getOdchylenie();
            srPrzeslaneZadania += procesor.getPrzeslaneZadania();
            srPytaniaObciazenie += procesor.getPytaniaObciazenie();
            srLicznikZapytan += procesor.getLicznikZapytan();
            srLicznikOdrzucone += procesor.getLicznikOdrzucone();
            srSrIloscKolejka += procesor.getSrIloscKolejka();
            srMaxKolejka += procesor.getMaxKolejka();
            srSrCzasKolejka += procesor.getSrCzasKolejka();
        }
        srSrObciazenie /= symulacjaWew.getLiczbaProcesorow();
        srOdchylenie /= symulacjaWew.getLiczbaProcesorow();
        srPrzeslaneZadania /= symulacjaWew.getLiczbaProcesorow();
        srPytaniaObciazenie /= symulacjaWew.getLiczbaProcesorow();
        srLicznikZapytan /= symulacjaWew.getLiczbaProcesorow();
        srLicznikOdrzucone /= symulacjaWew.getLiczbaProcesorow();
        srSrIloscKolejka /= symulacjaWew.getLiczbaProcesorow();
        srMaxKolejka /= symulacjaWew.getLiczbaProcesorow();
        srSrCzasKolejka /= symulacjaWew.getLiczbaProcesorow();
    }

    @Override
    public String toString() {
        return "Leniwy{" +
                "symulacjaWew=" + symulacjaWew +
                ", \niloscZadan=" + iloscZadan +
                ", \nczas=" + czas +
                ", \nsrSrObciazenie=" + srSrObciazenie +
                ", \nsrOdchylenie=" + srOdchylenie +
                ", \nsrPrzeslaneZadania=" + srPrzeslaneZadania +
                ", \nsrPytaniaObciazenie=" + srPytaniaObciazenie +
                ", \nsrLicznikZapytan=" + srLicznikZapytan +
                ", \nsrLicznikOdrzucone=" + srLicznikOdrzucone +
                ", \nsrIloscKolejka=" + srSrIloscKolejka +
                ", \nsrMaxKolejka=" + srMaxKolejka +
                ", \nsrSrCzasKolejka=" + srSrCzasKolejka +
                '}';
    }

    //funkcja wyświetlająca info dla każdego procesora

    public void wyswietlDaneProcesorow() {
        for (Procesor procesor : symulacjaWew.procesory) {
            System.out.println(procesor);
            System.out.println("\n\n");
        }
    }

    //*************** GETTERY I SETTERY ***************


    public Symulacja getSymulacjaWew() {
        return symulacjaWew;
    }

    public void setSymulacjaWew(Symulacja symulacjaWew) {
        this.symulacjaWew = symulacjaWew;
    }

    public int getIloscZadan() {
        return iloscZadan;
    }

    public void setIloscZadan(int iloscZadan) {
        this.iloscZadan = iloscZadan;
    }

    public double getSrObciazenie() {
        return srSrObciazenie;
    }

    public void setSrObciazenie(double srSrObciazenie) {
        this.srSrObciazenie = srSrObciazenie;
    }

    public double getSrOdchylenie() {
        return srOdchylenie;
    }

    public void setSrOdchylenie(double srOdchylenie) {
        this.srOdchylenie = srOdchylenie;
    }

    public double getSrPrzeslaneZadania() {
        return srPrzeslaneZadania;
    }

    public void setSrPrzeslaneZadania(int srPrzeslaneZadania) {
        this.srPrzeslaneZadania = srPrzeslaneZadania;
    }

    public int getSrPytaniaObciazenie() {
        return srPytaniaObciazenie;
    }

    public void setSrPytaniaObciazenie(int srPytaniaObciazenie) {
        this.srPytaniaObciazenie = srPytaniaObciazenie;
    }

    public int getSrLicznikZapytan() {
        return srLicznikZapytan;
    }

    public void setSrLicznikZapytan(int srLicznikZapytan) {
        this.srLicznikZapytan = srLicznikZapytan;
    }

    public int getSrLicznikOdrzucone() {
        return srLicznikOdrzucone;
    }

    public void setSrLicznikOdrzucone(int srLicznikOdrzucone) {
        this.srLicznikOdrzucone = srLicznikOdrzucone;
    }

    public int getSrIloscKolejka() {
        return srSrIloscKolejka;
    }

    public void setSrIloscKolejka(int srIloscKolejka) {
        this.srSrIloscKolejka = srIloscKolejka;
    }

    public int getSrMaxKolejka() {
        return srMaxKolejka;
    }

    public void setSrMaxKolejka(int srMaxKolejka) {
        this.srMaxKolejka = srMaxKolejka;
    }

    public int getSrSrCzasKolejka() {
        return srSrCzasKolejka;
    }

    public void setSrSrCzasKolejka(int srSrCzasKolejka) {
        this.srSrCzasKolejka = srSrCzasKolejka;
    }
}
