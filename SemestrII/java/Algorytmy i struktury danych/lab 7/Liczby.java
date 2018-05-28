
public class Liczby{
    int[] tablica;
    
    public Liczby(int size){
        tablica = new int[size];
        for(int i = 0; i < tablica.length; i++){
            tablica[i] = (int)(Math.random()*1000);
        }
    }
    
    public void wyswietl(){
        for(int i = 0; i < tablica.length; i++){
            System.out.println(i+": " + tablica[i]);
        }
    }
    
    public void sort(){
        for(int i = 0; i < tablica.length-1; i++){
            for(int j = i+1; j < tablica.length; j++){
                if(tablica[i] > tablica[j]){
                    int temp = tablica[i];
                    tablica[i] = tablica[j];
                    tablica[j] = temp;
                }
            }
        }
    }
    
    public void sortReverse(){
        for(int i = 0; i < tablica.length-1; i++){
            for(int j = i+1; j < tablica.length; j++){
                if(tablica[i] < tablica[j]){
                    int temp = tablica[i];
                    tablica[i] = tablica[j];
                    tablica[j] = temp;
                }
            }
        }
    }
}
