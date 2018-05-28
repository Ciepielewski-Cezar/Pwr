
public class Test{
    public static void main (String[] args){
        Graf graf = new Graf();
        
        Element e1 = new Element(5);
        graf.makeSet(e1);
        Element e2 = new Element(1);
        graf.makeSet(e2);
        
        Element e3 = new Element(3);
        graf.makeSet(e3);
        Element e4 = new Element(8);
        graf.makeSet(e4);
        
        
        graf.union(e1, e2);        
        graf.union(e2, e3);
        graf.union(e3, e4);
        
        graf.wyswietl(e1);
        graf.wyswietl(e2);
        graf.wyswietl(e3);
        graf.wyswietl(e4);
        
    }
}
