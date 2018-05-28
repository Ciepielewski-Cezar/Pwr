import java.lang.String;
import java.util.Arrays;

public class HashArray{
    String[] array;
    int arrSize;
    int itemsInArray;
    
    public HashArray(int size){
        arrSize = size;
        array = new String[arrSize];
        Arrays.fill(array, "-1");
        itemsInArray = 0;
    }
    
    private int hashFunction(String x){
        int index  = Integer.parseInt(x) % arrSize;
        return index;
    }
    
    public String get(String key){
        int indexHash = Integer.parseInt(key) % arrSize;
        
        while(array[indexHash] != "-1"){
            if(array[indexHash] == key){
                System.out.println("klucz " + key + " został znaleziony w indeksie: " + indexHash);
                return array[indexHash];
            }
            ++indexHash;
            indexHash = indexHash % arrSize;
        }
        return null;
    }
    
    public void put(String value){
        int index = hashFunction(value);
        System.out.println("indeks zmodulowany = " + index + " dla wartości " + value);
        
        while ( array[index] != "-1"){
            ++index;
            System.out.println("Kolizja, próba wstawienia w: " + index);
            index %= arrSize;
        }
        array[index] = value; 
        ++itemsInArray;
    }
    
    public boolean containsKey(String key){
       for (int i = 0; i < array.length; i++){
           if(array[i] == key){
               return true;
           }
       }
        return false;
    }
    
    public int size(){
        return arrSize;
    }
    
    public boolean isEmpty(){
        return itemsInArray == 0;
    }
    
    public void resize(){
        double ratio =(double) itemsInArray/array.length;
        
        if (ratio >= 0.50){
            int newLength = check(2 * array.length);
            String[] newArray = new String[newLength];
            Arrays.fill(newArray, "-1");
            for(int i = 0; i < array.length; i++){
                if(array[i] != "-1")
                    put(array[i], newArray);
            }
            array = newArray;
            System.out.println("rozmiar został zmieniony");
        }
        else System.out.println("rozmiar pozostał taki sam");
        arrSize = array.length;
    }
    
    public void put(String value, String[] newArray){
        int index  = Integer.parseInt(value) % newArray.length;
        
        while (newArray[index] != "-1"){
            ++index;
            index %= newArray.length;
        }
        newArray[index] = value; 
    }
    
    public void dump(){
        for(int i = 0; i < array.length; i++){
            System.out.format("| %3s " + " ", i);
        }
        System.out.print("|\n");
        for(int i = 0; i < array.length; i++){
            System.out.print("-------");
        }
        System.out.println("-");
        for(int i = 0; i < array.length; i++){
            if (array[i] == "-1"){
                System.out.print("|      ");
            }
            else System.out.print(String.format("| %3s " + " ", array[i]));
        }
        System.out.print("|\n");
    }
    
    public int check(int number){
        if (czyPierwsza(number) == false){
            check(number+1);
        }
        return number+1;
    }
    
    boolean czyPierwsza(int p){
        double pierw = Math.sqrt(p);
        
        if (p==1 || p==2){
            return false;
        }
        for (int i=2; i<=pierw; i++){
            if (p%i==0){
                return false;
            }
        }
        return true;
    }
    
}

  