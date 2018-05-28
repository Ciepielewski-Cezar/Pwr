
public class QuickSort implements ArraySort{
    private int porownania = 0;
    private int przepisania = 0;
    int[] tab;
    int[] pomocnicza;
    
    public QuickSort(int[] ciag){
        tab = new int[ciag.length];
        for(int i = 0; i<tab.length; i++){
            tab[i] = ciag[i];
        }
    }
    
    public int[] sortuj(){
        if (tab == null || tab.length == 0){
            System.out.println("Zła tablica.");
        }
        quickSort(0, tab.length - 1);
        return tab;
    }
    
    private void quickSort(int poczatek, int koniec){
        if(koniec - poczatek > 1){
            int i = poczatek;
            int j = koniec;
            int piwot = tab[poczatek + (koniec-poczatek)/2];
            
            while(i <= j){
                while(tab[i] < piwot){
                    i++;
                    
                    porownania++;
                }
                while(tab[j] > piwot){
                    j--;
                    
                    porownania++;
                }
                if(i <= j){
                    int temp =  tab[i];
                    tab[i] = tab[j];
                    tab[j] = tab[i];
                    i++;
                    j--;
                    
                    porownania++;
                    przepisania += 3;
                }
            }
            if(poczatek < j){
                quickSort(poczatek, j);
            }
            if(i < koniec){
                quickSort(i, koniec);
            }
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
        