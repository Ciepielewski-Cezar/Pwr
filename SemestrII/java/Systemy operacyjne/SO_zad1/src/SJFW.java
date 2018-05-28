import java.util.ArrayList;

/**
 * Created by Czarek on 24.03.2017.
 */
public class SJFW {
    Cykl wew = new Cykl(limit);
    private ArrayList<Proces> lista = new ArrayList<>();
    private static int limit;  //ilość procesów w algorytmie
    int[] czasy = new int[limit];   //do przechowywania czasu oczekiwania procesów
    private int czasAlgorytmu = 0;  //średni czas całego algorytmu
    private int suma = 0;  //suma długości procesów w algorytmie

    public SJFW(Cykl zew) {
        limit = zew.getLimit();
        for (Proces p : zew.cykl) {
            lista.add(new Proces(p));
        }
    }

    public int symulacja() {
        wyrownaj();
        //wyswietl();
        dlugoscSort();
        //System.out.println("\n");
        //wyswietl();
        //System.out.println("\n");

        long czas = 0;
        int licznik = 0;
        int i = 0;
        boolean czyDodany = false;

        for (; licznik < limit; ) {
            for (Proces x : lista) {
                while (czas >= x.getCzasWstawienia() && !x.isWykonany() && !czyDodany && !sprawdz(czas, x)) {
                    if (x.getDlugoscProcesu() - x.getRealnaDP() > 0) {
                        x.setRealnaDP(x.getRealnaDP() + 1);

                        if (suma - x.getCzasWstawienia() >= 0) {
                            x.setCzasOczekiwania(suma - x.getCzasWstawienia()); // wcześniej x.getCzasOczekiwania() - x.getCzasWstawienia()
                        } else {
                            x.setCzasOczekiwania(0);
                        }

                        suma += 1;
                        czas += 1;

                    } else {
                        //x.setRealnaDP(x.getRealnaDP() + 1); //zmienione z x.setRealnaDP(x.getDlugoscProcesu());

                        if (suma - x.getCzasWstawienia() - x.getDlugoscProcesu() >= 0) {
                            x.setCzasOczekiwania(suma - x.getCzasWstawienia() - x.getDlugoscProcesu());
                        } else x.setCzasOczekiwania(0);

                        //suma += 1; do wyrzucenia
                        //czas += 1; do wyrzucenia
                        x.setWykonany(true);
                        licznik++;
                        czyDodany = true;
                        //System.out.println(i + ": " + x); //wyświetlenie po kolei wyników
                        i++;
                    }
                }
            }
            if (!czyDodany) czas++;
            czyDodany = false;
        }

        long s = 0;
        for (Proces p : lista)
            s += p.getCzasOczekiwania();
        czasAlgorytmu = (int) s / limit;
        /*
        System.out.println("\n");
        wyswietl();
        System.out.println("\n");
        sortuj();
        wyswietl();
        */
        System.out.print("Czas symulacji SJFW: ");

        return czasAlgorytmu;
    }


    private boolean sprawdz(long cz, Proces obecny) {
        for (Proces inny : lista) {
            if (cz >= inny.getCzasWstawienia() && !inny.isWykonany() && inny.getDlugoscProcesu() < (obecny.getDlugoscProcesu() - obecny.getRealnaDP())) {
                return true;
            }
        }
        return false;
    }


    private void dodajCykl(Cykl jakis) {
        wew.cykl = jakis.cykl;
        for (Proces r : wew.cykl) {
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

    private void dlugoscSort() {
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).getDlugoscProcesu() > lista.get(j).getDlugoscProcesu()) {
                    Proces temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                }
            }
        }
    }

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

    private void wewSort(long granica) {
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).getDlugoscProcesu() > lista.get(j).getDlugoscProcesu() && lista.get(j).getCzasWstawienia() <= granica
                        && !lista.get(i).isWykonany() && !lista.get(j).isWykonany()) {
                    Proces temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                }
            }
        }
    }
}