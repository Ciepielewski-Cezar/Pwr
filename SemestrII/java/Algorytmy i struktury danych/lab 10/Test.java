import java.util.Comparator;

public class Test{

    public static void main (String[] args) {


        IntComparator intComparator = new IntComparator();
        Integer[] tablica = {5, 10, 8, 15, 20 ,13};
        Kopiec kopiec = new Kopiec(10, intComparator);
        for (int i = 0; i < tablica.length; i++) {
            System.out.println(tablica[i]);
        }
        System.out.println();
        kopiec.sort(tablica,intComparator);
        for (int i = 0; i < tablica.length; i++) {
            System.out.println(tablica[i]);
        }
        System.out.println();
        /*
        kopiec.enqueue(5);
        kopiec.enqueue(10);
        kopiec.enqueue(8);
        kopiec.enqueue(15);
        kopiec.enqueue(20);
        kopiec.enqueue(13);
        kopiec.wyswietl();
        System.out.println();
        System.out.println(kopiec.dequeue());
        System.out.println(kopiec.dequeue());
        kopiec.wyswietl();
        
        kopiec.priorytet(3,1);
        System.out.println();
        kopiec.wyswietl();
        */


    }
}

