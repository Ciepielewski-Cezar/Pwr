

public class TestBFS{
     public static void main(String args[]){
        AlgorytmBFS g = new AlgorytmBFS(9);
 
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(1, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(7, 8);
        
        int wierzcholek =  4;
 
        System.out.println("zaczynamy od wierzchołka " + wierzcholek);
 
        g.wykonajBFS(wierzcholek);
    }
}