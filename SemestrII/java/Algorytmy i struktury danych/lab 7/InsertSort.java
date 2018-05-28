
public class InsertSort implements ArraySort{
    private int porownania = 0;
    private int przepisania = 0;
    int [] tab;
    
    public InsertSort(int[] ciag){
        tab = new int[ciag.length];
        for (int i = 1; i < ciag.length; i++){
            tab[i] = ciag[i];
        }
    }
        
    public int[] sortuj(){
        for (int i = 1; i < tab.length; i++){
            int j = 0;
            int element = tab[i];
            
            while(j<i && tab[j] <= element){
                j++;
                
                porownania++;
            }
            for(int k = i; k > j; k--){
                tab[k] = tab[k-1];
                
                przepisania++;
            }
            tab[j] = element;
            
            przepisania++;
        }
        
        return tab;
    }
    
    public void wyswietl(){
        for(int i = 0; i < tab.length; i++){
            System.out.println(i+": " + tab[i]);
        }
    }
    
    public int getPorownania(){ return porownania; }
    public int getPrzepisania(){ return przepisania; }
    
}
            
