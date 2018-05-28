
public class Test{
    public static void main (String[] args){
        Graf graf = new Graf();
        
        Element e1 = new Element(5);
        graf.makeSet(e1);
        Element e2 = new Element(1);
        graf.makeSet(e2);
        
        Element d1 = new Element(9);
        Element d2 = new Element(67, d1);
        Element d3 = new Element(13, d2);
        
        graf.union(e1, e2);
        graf.wyswietl(e1);
        graf.wyswietl(d3);
    }
}
