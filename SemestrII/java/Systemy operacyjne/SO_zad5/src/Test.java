import java.util.Random;

/**
 * Created by Czarek on 01.06.2017.
 */
public class Test {
    public static void main(String[] args) {
        int liczbaProcesorow = 50;               //50 - 100  liczba procesorów
        int liczbaZadan = 4500;                   //liczba zadań na każdy procesor
        double[] progiMaxProcesorow = {0.7, 0.95};//zmienna generująca maksymalne progi dla procesorów w podanym zakresie
        double[] progiMinProcesorow = {0.2, 0.5}; //zmienna generująca minimalne progi dla procesorów w podanym zakresie
        int iloscZapytan = 30;                   //ilość pytań do procesorów dla algorytmu leniwego
//----------------------------------------------------------------------------------------------------------------------

        Symulacja symulacja = new Symulacja(liczbaProcesorow, progiMaxProcesorow, progiMinProcesorow, iloscZapytan, liczbaZadan);


        /*
        README:
        Wszystko powinno działać. Uważać z dobieraniem danych bo może bardzo długo generować wyniki
        Sterowanie właściwościami zadania w klasie Zadanie
        Czasami odchylenie równa się NaN. Nie wiem dlaczego
         */


        Leniwy leniwy = new Leniwy(symulacja);
        leniwy.aktywuj();
        System.out.println(leniwy);
        System.out.println("\n\n\n\n");



        Pracowity pracowity = new Pracowity(symulacja);
        pracowity.aktywuj();
        System.out.println(pracowity);
        System.out.println("\n\n\n\n");



        Milosierny milosierny = new Milosierny(symulacja);
        milosierny.aktywuj();
        System.out.println(milosierny);
        System.out.println("\n\n\n\n");



        //na konsultacje


    }
}




