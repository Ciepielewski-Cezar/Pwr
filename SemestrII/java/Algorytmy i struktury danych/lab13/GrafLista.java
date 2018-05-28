import java.util.LinkedList;

public class GrafLista{
    private LinkedList<Integer>[] lista;
    
    public GrafLista(int iloscWierzcholkow){
        lista = new LinkedList[iloscWierzcholkow];
        
        for (int i = 0; i < lista.length; i++){
            lista[i] = new LinkedList<>();
        }
    }
    
    public void add(int a, int b){
         lista[a].add(b);
         lista[b].add(a);
    }
    
    public void wyswietl() {
        for (int i = 0; i < lista.length; i++){
            System.out.println(i+": " + lista[i]);
        }
    }
    
    public void wyswietlPolaczenia(){
        for (int i = 0; i < lista.length; i++){
            System.out.println();
            for (int j = 0; j < lista[i].size(); j++){
                    System.out.print("{" + i + ", " + lista[i].get(j) + "}");
            }
        }
    }
        
    
}
