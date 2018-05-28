
public class Test{
    public static void main(String[] args){
        Drzewo drzewo = new Drzewo();
        drzewo.insert(50);
        drzewo.insert(30);
        drzewo.insert(70);
        drzewo.insert(20);
        drzewo.insert(40);
        drzewo.insert(60);
        drzewo.insert(80);
        /*
        drzewo.insert(81);
        drzewo.insert(79);
        drzewo.insert(82);
        drzewo.insert(19);
        */
        System.out.println("pre-order");
        drzewo.preOrder();
        System.out.println();
        System.out.println("in-order");
        drzewo.inOrder();
        System.out.println();
        System.out.println("post-order");
        drzewo.postOrder();
        System.out.println();
        System.out.println();
        System.out.println(drzewo.find(30));
        System.out.println(drzewo.find(100));
        System.out.println();
        System.out.println("minimum: " + drzewo.min());
        System.out.println();
        System.out.println("maximum: " + drzewo.max());
        System.out.println();
        System.out.println("wysokość: " + drzewo.wysokosc());
        System.out.println();
        System.out.println("liczba węzłów: " + drzewo.liczbaWezlow());
        System.out.println();
        System.out.println("liczba liści/węzłów zewnętrznych: " + drzewo.liczbaLisci());
        System.out.println();
        System.out.println("liczba węzłów wewnętrznych: " + drzewo.liczbaWew());
        System.out.println();
        System.out.println("poziomy: ");
        drzewo.poziomami();
        

    }
}
