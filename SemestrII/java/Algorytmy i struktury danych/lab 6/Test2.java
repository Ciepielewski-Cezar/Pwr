public class Test2{
    public static void main(String[] args){
        int size = 6;
        Samochod[] tab = new Samochod[size];
        tab[0] = new Samochod("mercedes", "zielony", 2003);
        tab[1] = new Samochod("bmw", "zielony", 2003);
        tab[2] = new Samochod("opel", "szary", 2015);
        tab[3] = new Samochod("audi", "niebieski", 2017);
        tab[4] = new Samochod("toyota", "czarny", 2010);
        tab[5] = new Samochod("toyota", "czarny", 2006);
        
        CompoundComparator zlozony = new CompoundComparator();
        zlozony.addComparator(KolorComparator.INSTANCE);
        zlozony.addComparator(RokComparator.INSTANCE);
        zlozony.addComparator(MarkComparator.INSTANCE);
        
        
        SelectSort ss = new SelectSort(zlozony);
        ss.sort(tab,size);
        
        for (int i = 0; i < tab.length ; i++) {
            System.out.println(tab[i]);
        }

    }
}