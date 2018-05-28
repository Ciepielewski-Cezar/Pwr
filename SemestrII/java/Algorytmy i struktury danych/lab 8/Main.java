
public class Main{
    public static void main(String[] args){
        int proby = 1000;
        int zakres = 2000;
        int szukana;
        Tablica tablica = new Tablica(1000, 2000);
        tablica.wypelnij();
        int a, c;
        double b, d, srednia;
        
        for( int i = 0; i < proby; i++){
            szukana = (int)(Math.random() * zakres);
            tablica.liniowe(szukana);
        }
        
        a = tablica.trafioneL;
        b = tablica.porownaniaTL/tablica.trafioneL;
        c = tablica.chybioneL;
        d = tablica.porownaniaNL/tablica.chybioneL;
        srednia = (tablica.porownaniaTL + tablica.porownaniaNL)/proby;
        
        System.out.println("liczba wyszukań trafionych: " + a);
        System.out.println("liczba wyszukań chybionych: " + c);
        System.out.println("średnia liczba porównań gdy trafiony: " + b);
        System.out.println("średnia liczba porównań gdy chybiony: " + d);
        System.out.println("łączna średnia porównań: " + srednia);
    }   
}
        
        