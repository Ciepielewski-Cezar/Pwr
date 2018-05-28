
public class Main2{
    public static void main(String[] args){
        int proby = 1000;
        int zakres = 2000;
        int szukana;
        Tablica tablica = new Tablica(1000, 2000);
        tablica.wypelnij();
        tablica.shell();
        int a, c;
        double b, d, srednia;
        
        for( int i = 0; i < proby; i++){
            szukana = (int)(Math.random() * zakres);
            tablica.binarne(szukana);
        }
        
        a = tablica.trafioneB;
        b = tablica.porownaniaTB/tablica.trafioneB;
        c = tablica.chybioneB;
        d = tablica.porownaniaNB/tablica.chybioneB;
        srednia = (tablica.porownaniaTB + tablica.porownaniaNB)/proby;
        
        System.out.println("liczba wyszukań trafionych: " + a);
        System.out.println("liczba wyszukań chybionych: " + c);
        System.out.println("średnia liczba porównań gdy trafiony: " + b);
        System.out.println("średnia liczba porównań gdy chybiony: " + d);
        System.out.println("łączna średnia porównań: " + srednia);
    }   
}
        
        