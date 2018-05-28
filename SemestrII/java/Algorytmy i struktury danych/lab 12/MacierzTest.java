

public class MacierzTest{
    public static void main (String[] args){
        GrafMacierz gm = new GrafMacierz(5);
        
        gm.add(1,4);
        gm.add(2,4);
        gm.add(3,1);
        gm.add(0,3);
        System.out.println("wyświetl macierz: ");
        gm.wyswietl();
        System.out.println();
        System.out.println();
        
        System.out.println("wyświetl połączenia: ");
        gm.wyswietlPolaczenia();
    }
}