
public class Test{
    public static void main (String[] args){
        HashArray firstArr = new HashArray(29); //liczba pierwsza
        String[] elementsToAdd = {"100", "510", "170", "268", "398", "235", "802", "900", "723", "699", "1", "50"
                                 ,"531", "32", "4", "91", "130", "401", "951", "9", "41"
                                 };
        
        for( int i = 0; i < elementsToAdd.length; i++){
            firstArr.put(elementsToAdd[i]);
        }
        System.out.println();
        System.out.println("get key 170: " );
        firstArr.get("170");
        
        System.out.println();
        System.out.println("znajdź klucz 100");
        System.out.println(firstArr.containsKey("100 (istnieje)"));
        
        System.out.println("znajdź klucz 11");
        System.out.println(firstArr.containsKey("11 (nie istenieje)"));
        
        System.out.println();
        firstArr.dump();
        
        System.out.println();
        System.out.println("size():");
        System.out.println(firstArr.size());
        
        System.out.println("isEmpty():");
        System.out.println(firstArr.isEmpty());
        
        System.out.println();
        firstArr.resize();
        
        System.out.println();
        System.out.println("size():");
        System.out.println(firstArr.size());
        
        System.out.println();
        firstArr.dump();
    }
}
