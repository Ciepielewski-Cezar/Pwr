import java.lang.String;
 import java.util.ArrayList;
import java.util.List;


public class Lista{
    List<List<String>> array;
    int arrSize;
    int itemsInArray;
    
    public Lista(int size){
        arrSize = size;
        array = new ArrayList<List<String>>(arrSize);
        
        for (int i = 0; i < arrSize; i++) {
            List<String> tmp = new ArrayList<String>();
            array.add(tmp);
        }
    }
    
    private int hashFunction(String word){
        int length = word.length();
        char[] str = new char[length+1];
        word.getChars(0,length,str,0);
        int sum = 0;
        
        for (int i=0;i<length;i++)
                sum = sum + (int)str[i];

        sum = sum % arrSize;
        return sum;
    }
    
    public void get(String word){
       int sum = hashFunction(word);
       List tmp = array.get(sum);
       System.out.println(array.get(sum));
    }
    
    
    public void put(String word){
        int sum = hashFunction(word);
        List tmp = array.get(sum);
        tmp.add(word);
    }
    
    public boolean containsKey(String word){
       int sum = hashFunction(word);
       List tmp = array.get(sum);
       
       if(tmp.contains(word)){
           return true;
       }
       else{
           return false;
       }
    }
    
    public int size(){
        return arrSize;
    }
    
    public boolean isEmpty(){
        return itemsInArray == 0;
    }
    
    public void dump(){
        for (int i = 0; i< array.size(); i++){
            for (int j = 0; j< array.get(i).size(); i++){
                if(array.get(i).get(j) != "" || array.get(i).get(j) != null){
                    System.out.print("| " +array.get(i).get(j) + " ");
                }
                else  System.out.print("|     ");
            }
            System.out.print("|\n");
        }
    }
   
    
}

  