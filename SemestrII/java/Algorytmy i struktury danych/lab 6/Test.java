

public class Test{
    public static void main(String[] args){
        Samochod[] tab = new Samochod[5];
        tab[0] = new Samochod("bmw", "zielony", 1990);
        tab[1] = new Samochod("mercedes", "zielony", 2003);
        tab[2] = new Samochod("opel", "szary", 2015);
        tab[3] = new Samochod("audi", "niebieski", 2017);
        tab[4] = new Samochod("toyota", "czarny", 2010);
        
        SelectSort ss = new SelectSort(KolorComparator.INSTANCE);
        ss.sort(tab);
        
        for (int i = 0; i < tab.length ; i++) {
            System.out.println(tab[i]);
        }

    }
}
        
