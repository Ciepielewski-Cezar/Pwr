public class Tablica{
    Towar[] tab;

    
    public Tablica(){
        this.tab = new Towar[4];
        tab[0] =new Towar("drukarka", 3.0, 600.0, 1800.0);
        tab[1] =new Towar("komputer", 1.0, 8000.0, 8000.0);
        tab[2] =new Towar("monitor", 1.0, 750.0, 750.0);
        tab[3] =new Towar("glosniki", 4.0, 50.0, 200.0);
    }
    
    private class TowarFiltr implements Predicate{
        public boolean accept(Object z){
            return ((Towar)z).getCena()>650;
        }
    }
    
    public void wyswietl(){
        IteratorTab it = new IteratorTab(tab);
        it.first();
        
        while(!it.isDone()){
            System.out.println(it.current().toString());
            it.next();
        }
    }
    
    public void zmianaC(String naz, Double c){
        IteratorTab it = new IteratorTab(tab);
        for(it.first(); !it.isDone(); it.next()){
            Towar temp = (Towar)it.current();
            if((temp.getNazwa()).equals(naz)){
                temp.setCena(c);
                temp.setWartosc(c*temp.getIlosc());
            }
        }
    }
    
    public void filtr(){
        IteratorTab i = new IteratorTab(tab);
        PreCena p = new PreCena();
        IteratorFiltrujacy it = new IteratorFiltrujacy(i,p);
        it.first();
        
        while(!it.isDone()){
            System.out.println((Towar)it.current());
            //it.filtrujDoPrzodu();
            it.next();
        }
    }
    
    public void powyzej(){
        Iterator it= new IteratorTab(tab); 
        Iterator fit= new IteratorFiltrujacy(it,new TowarFiltr());
        fit.first();
        
        while(!fit.isDone()){
            Towar tow=(Towar)fit.current();
            System.out.println(tow);
            fit.next();
        }
    } 
}

                

    