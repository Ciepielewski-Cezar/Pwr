
public class MergeSort implements ArraySort{
    private int porownania = 0;
    private int przepisania = 0;
    int[] tab;
    int[] pomocnicza;
    
    public MergeSort(int[] ciag){
        tab = new int[ciag.length];
        for (int i = 1; i < ciag.length; i++){
            tab[i] = ciag[i];
        }
    }
    
    public int[] sortuj(){
        int size = tab.length;
        this.pomocnicza = new int[size];
        mergeSort(0, size-1);
        return tab;
    }
    
    private void mergeSort(int pocz, int kon){
        if(pocz < kon){
            int sr = (pocz + kon) / 2;
            
            mergeSort(pocz, sr);
            mergeSort(sr+1, kon);
            merge(pocz, sr, kon);
        }
    }
    
    private  void merge(int poczatek, int srodek, int koniec){
        for(int i = poczatek; i <= koniec; i++){
            pomocnicza[i] = tab[i];
            
            przepisania++;
        }
        int i = poczatek;
        int j = srodek + 1;
        int k = poczatek;
        
        while(i <= srodek && j <= koniec){
            porownania += 2;
            
            if(pomocnicza[i] <= pomocnicza[j]){
                tab[k] = pomocnicza[i];
                i++;
                
                porownania++;
                przepisania++;
            } else {
                tab[k] = pomocnicza[j];
                j++;
                porownania++;
                przepisania++;
            }
            k++;
        }
        while(i <= srodek){
            tab[k] = pomocnicza[i];
            k++;
            i++;
            
            porownania++;
            przepisania++;
        }
    }
    
    public void wyswietl(){
        for(int i = 0; i < tab.length; i++){
            System.out.println(i+": " + tab[i]);
        }
    }
    
    public int getPorownania(){ return porownania; }
    public int getPrzepisania(){ return przepisania; }
}
        
        
    