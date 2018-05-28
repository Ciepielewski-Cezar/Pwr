/**
 * Created by Czarek on 06.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        int ilosc = 8;
        System.out.println("Ilość liczb = " + ilosc);
        Liczby liczby = new Liczby(ilosc);
        
        //Sortowanie liczb:
        //liczby.sort();
        //liczby.sortReverse();
        
        liczby.wyswietl();
        System.out.println("\n\n\n");

        System.out.println("InsertSort");
        InsertSort insertSort = new InsertSort(liczby.tablica);
        insertSort.sortuj();
        insertSort.wyswietl();
        System.out.println("porównania: " + insertSort.getPorownania());
        System.out.println("przepisania: " + insertSort.getPrzepisania());
        System.out.println("\n\n\n");


        System.out.println("MergeSort");
        MergeSort mergeSort = new MergeSort(liczby.tablica);
        mergeSort.sortuj();
        mergeSort.wyswietl();
        System.out.println("porównania: " + mergeSort.getPorownania());
        System.out.println("przepisania: " + mergeSort.getPrzepisania());
        System.out.println("\n\n\n");
        
        
        System.out.println("QuickSort");
        QuickSort quickSort = new QuickSort(liczby.tablica);
        quickSort.sortuj();
        quickSort.wyswietl();
        System.out.println("porównania: " + quickSort.getPorownania());
        System.out.println("przepisania: " + quickSort.getPrzepisania());


    }
}
