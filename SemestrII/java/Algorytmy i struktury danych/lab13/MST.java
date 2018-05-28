import java.util.*;
import java.lang.*;
import java.io.*;

public class MST{
    private int wierzcholki = 6;
    
    
    //algorytm Prima
    public void wykonajMST(int graf[][]){
        int parent[] = new int[wierzcholki]; //do konstrukcji
        int key[] = new int [wierzcholki];   //waga dla krawędzi
        Boolean mstSet[] = new Boolean[wierzcholki]; //zawarte/nie zawarte w MST
 
        for (int i = 0; i < wierzcholki; i++){
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
 
        key[0] = 0;     
        parent[0] = -1; //pierwszy węzeł to root
        
        for (int licznik = 0; licznik < wierzcholki-1; licznik++){
            int u = minKey(key, mstSet); //szukamy najmniejszego priorytetu
            mstSet[u] = true; //ustawiamy na true
 
            for (int i = 0; i < wierzcholki; i++){
                if (graf[u][i]!=0 && mstSet[i] == false && graf[u][i] <  key[i]){
                    parent[i] = u;
                    key[i] = graf[u][i];
                }
            }
        }
 
        wyswietlMST(parent, wierzcholki, graf);
    }
       
    //do znajdowania węzła z najniższym priorytetem
    int minKey(int key[], Boolean mstSet[]){
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
 
        for (int i = 0; i < wierzcholki; i++){
            if (mstSet[i] == false && key[i] < min){
                min = key[i];
                minIndex = i;
            }
        }
 
        return minIndex;
    }
    
    
    private void wyswietlMST(int parent[], int n, int graf[][]){
        System.out.println("krawędzie:   waga: ");
        for (int i = 1; i < wierzcholki; i++){
            System.out.println(parent[i] + " - "+ i + "    \t"+ graf[i][parent[i]]);
        }
    }
}
