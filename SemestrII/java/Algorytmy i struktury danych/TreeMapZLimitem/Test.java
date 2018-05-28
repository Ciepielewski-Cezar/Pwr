public class Test{
    //TEST
        public static void main(String[] args){
        Tablica tab = new Tablica(5);
        
        Robotnik robotnik1 = new Robotnik("Kowalski", 1.0, 120, 15, 0);
        Robotnik robotnik2 = new Robotnik("Mierczynski", 0.5, 60, 9, 6);
        Robotnik robotnik3 = new Robotnik("Nowak", 1.5, 170, 12, 3);
        Urzednik urzednik1 = new Urzednik("Szelmanowski", 1.0, 2000, 0);
        Urzednik urzednik2 = new Urzednik("Miskowski", 1.2, 1500, 3000);
        Urzednik urzednik3 = new Urzednik("Karnisik", 1.0, 1000, 1000);
        
        tab.dodajPracownika(robotnik1);
        tab.dodajPracownika(robotnik2);
        tab.dodajPracownika(robotnik3);
        tab.dodajPracownika(urzednik1);
        tab.dodajPracownika(urzednik2);
        tab.dodajPracownika(urzednik3);
        
        tab.listaPracownikow();

        System.out.println(tab.znajdzPrac("Kowalski"));
        System.out.println(tab.znajdzPrac("efsfse"));
        
        System.out.println(tab.czyJest(robotnik1));
        System.out.println(tab.czyJest(urzednik1));
        tab.sumaPrac();
        
        System.out.println(tab.usunPracownika("Nowak"));
        tab.listaPracownikow();
        
        tab.sumaWyplat();
        
        tab.listaRobotnikow();
        tab.listaUrzednikow();
        tab.listaPlac();
    }
}

/*
 * 
 * public void dodajPracownika(Pracownik prac){
        if(tab.length == zatrudPrac){
            return;
        }
        else{
            tab[zatrudPrac] = prac;
            zatrudPrac++;
        }
    }
    
    
    
    
    public void dodajRobotnika(String n, double e, double p, double s, double nad){
        if(tab.length == zatrudPrac)return;
        Robotnik rob = new Robotnik(n,e,p,s,nad);
        tab[zatrudPrac] = rob;
        zatrudPrac++;
    }
 */