import java.io.*;
import java.util.*;

public class AlgorytmDFS{ //w głąb
    private int wierzcholki;
    private LinkedList<Integer> lista[];
 
    AlgorytmDFS(int w){
        wierzcholki = w;
        lista = new LinkedList[wierzcholki];
        for (int i=0; i<wierzcholki; ++i){
            lista[i] = new LinkedList();
        }
    }
    
     void addEdge(int a, int b){
        lista[a].add(b);
        lista[b].add(a);
    }
    
    void wykonajDFS(int w, boolean odwiedzone[]){
        odwiedzone[w] = true;
        System.out.print(w+" ");
        Iterator<Integer> iterator = lista[w].listIterator();
        
        while (iterator.hasNext()){
            int n = iterator.next();
            
            if (!odwiedzone[n]){
                wykonajDFS(n, odwiedzone);
            }
        }
    }
 

    void DFS(int w){
        boolean odwiedzone[] = new boolean[wierzcholki];
        wykonajDFS(w, odwiedzone);
    }
}