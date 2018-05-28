
public class Main{
    public static void main(String[] args){
        ListaWiazana lista = new ListaWiazana ();
        
        System.out.println("dodaj nowe wartości");
        lista.add("1");
        
        lista.add("2");
        lista.add("3");
        lista.wyswietl();
        
        System.out.println("czysczenie i dodanie nowych");
        lista.clear();
        lista.add("1");
        lista.add("2");
        lista.add("4");
        lista.add("5");
        lista.wyswietl();
        System.out.println("dodaj 3 na pozycje 2");
        lista.insert(2,"3");
        lista.wyswietl();
        System.out.println("usuń z 2 pozycji");
        lista.delete(2);
        lista.wyswietl();
        System.out.println("zróć wartość z 3 pozycji");
        System.out.println(lista.get(3));
        System.out.println("zwróć rozmiar");
        System.out.println(lista.size());
        System.out.println("zmień wartość z 1 pozycji i zwróć starą");
        System.out.println(lista.set(0,"45"));
        lista.wyswietl();
        System.out.println("czy jest element o wartości 4 jest na liście");
        System.out.println(lista.contains("4"));
        System.out.println("czy pusta");
        System.out.println(lista.isEmpty());
        
    }
   
}