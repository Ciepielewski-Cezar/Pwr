import java.util.ArrayList;

public class Cykl {
    ArrayList<Proces> cykl;
    private int limit;


    public Cykl(int limit) {
        this.limit = limit;
        cykl = new ArrayList<Proces>(limit);
    }

    public int getLimit() {
        return limit;
    }




    public void uzupelnij() {
        int i = 0;
        while (i < limit) {
            Proces p = new Proces();
            cykl.add(p);
            i++;
        }
        sort();
    }



    public void wyswietl() {
        //sort();
        int i = 0;
        for (Proces p : cykl) {
            System.out.println(i + ": " + p);
            i++;
        }
    }

    public void srednia() {
        int srednia = 0;
        int i = 0;
        for (Proces p : cykl) {
            srednia += (int) p.getDlugoscProcesu();
            i++;
        }
        System.out.print("\nśrednia długość procesu: ");
        System.out.print(srednia / i);
    }
    public void suma(){
        int suma = 0;
        for (Proces p : cykl) {
            suma += p.getDlugoscProcesu();
        }
        System.out.print("\nsuma długości proceów: ");
        System.out.print(suma);
    }

    public void tabela() {
        int[] tab = new int[14];
        for (Proces p : cykl) {
            int liczba = (int) p.getDlugoscProcesu();
            int reszta = liczba / 10;
            for (int i = 0; i < tab.length; i++) {
                if (reszta == i) tab[i] += 1;
            }
        }
        for (int i = 0; i < tab.length; i++) {
            System.out.println(i + ": " + tab[i]);
        }
    }

    private void sort() {
        for (int i = 0; i < cykl.size() - 1; i++) {
            for (int j = i + 1; j < cykl.size(); j++) {
                if (cykl.get(i).getCzasWstawienia() > cykl.get(j).getCzasWstawienia()) {
                    Proces temp = cykl.get(i);
                    cykl.set(i, cykl.get(j));
                    cykl.set(j, temp);
                }
            }
        }
    }
}