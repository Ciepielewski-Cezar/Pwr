import java.util.LinkedList;

public class GrafMacierz{
    private boolean[][] macierz;
    
    
    public GrafMacierz(int iloscWierzcholkow){
        macierz = new boolean[iloscWierzcholkow][iloscWierzcholkow];
        
        for (int i = 0; i <  macierz.length; i++){
             macierz[i][i] = true;
        }
    }
    
    public void add(int a, int b){
        macierz[a][b] = true;
        macierz[b][a] = true;
    }
    
    public void wyswietl(){
        for (int i = 0; i < macierz.length; i++) {
            System.out.println();
            for (int j = 0; j < macierz[i].length; j++) {
                System.out.printf("%3d", macierz[i][j] ? 1 : 0);
            }
        }
    }
    
    public void wyswietlPolaczenia(){
        boolean pusty = true;
        for (int i = 0; i < macierz.length; i++) {
            System.out.println();
            for (int j = 0; j < macierz[i].length; j++) {
                if(macierz[i][j] && i != j){
                    System.out.print("{" + i + ", " + j + "}");
                    pusty = false;
                }
            }
        }
        if(pusty){
            for (int i = 0; i < macierz.length; i++) {
                System.out.println("{" + i +"}");
            }
        }
    }

        

}
        
