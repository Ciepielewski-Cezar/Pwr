import java.io.*;
import java.util.*;

public class AlgorytmBFS{ //w głąb
    private int wierzcholki;
    private LinkedList<Integer> lista[];
    private ArrayList<Integer> wynik;
    
 
    AlgorytmBFS(int w){
        wierzcholki = w;
        lista = new LinkedList[wierzcholki];
        wynik = new ArrayList<>();
        
        for (int i=0; i<wierzcholki; ++i){
            lista[i] = new LinkedList();
        }
    }
    
     void addEdge(int a, int b){
        lista[a].add(b);
        lista[b].add(a);
    }
    
    void wykonajBFS(int w){
        boolean odwiedzone[] = new boolean[wierzcholki];
        LinkedList<Integer> kolejka = new LinkedList<Integer>();
        odwiedzone[w] = true;
        kolejka.add(w);
        
        while (kolejka.size() != 0){
            w = kolejka.poll();
            System.out.println();
            System.out.println("przetwarzany: " + w + " ");
            wynik.add(w);
            
            Iterator<Integer> iterator = lista[w].listIterator();
            
            while (iterator.hasNext()){
                int n = iterator.next();
                
                if (!odwiedzone[n]){
                    odwiedzone[n] = true;
                    kolejka.add(n);
                }
            }
            System.out.println("zawartość kolejki: ");
            
            for(int i = 0; i < kolejka.size(); i++){
                System.out.print(kolejka.get(i) + " ");
            }
            
            System.out.println();
        }
        
        System.out.println("wynik: ");
        
        for(Integer liczba : wynik){
            System.out.print(liczba + " ");
        }
    }   
}