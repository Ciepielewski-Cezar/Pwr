

public class ListaTest{
    public static void main (String[] args){
        GrafLista g = new GrafLista(5);
        
        g.add(0,3);
        g.add(4,1);
        g.add(1,3);
        
        System.out.println("wyświetl listę: ");
        g.wyswietl();
        System.out.println();
        
        System.out.println("wyświetl połączenia: ");
        g.wyswietlPolaczenia();
    }
}
