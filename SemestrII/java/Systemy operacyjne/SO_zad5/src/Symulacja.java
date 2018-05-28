import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class Symulacja {
    List<Procesor> procesory;        //lista procesorów
    private int liczbaProcesorow;    //ilość procesorów

    double[] max;                    //przedział wartości progów maksymalnych dla procesu
    double[] min;                    //przedział wartości progów minimalnych dla procesu
    private static int zapytania;                   //ilość możliwych zapytań do innych procesorów



    public Symulacja(int lProc, double[] maks, double[] mini, int iloscZapytan, int lZadan){
        liczbaProcesorow = lProc;
        procesory = new ArrayList<>(liczbaProcesorow);
        max = maks;
        min = mini;
        zapytania = iloscZapytan;

        for (int i = 0; i < liczbaProcesorow; i++){
            Procesor p = new Procesor(max, min, zapytania,lZadan, i);
            procesory.add(p);
        }

    }

    public Symulacja(Symulacja inna) {
;       this.liczbaProcesorow = inna.getLiczbaProcesorow();
        this.max = inna.max;
        this.min =inna.min;
        this.zapytania = getZapytania();

        this.procesory = new ArrayList<>();

        for (Procesor p: inna.getProcesory()
             ) {
            this.procesory.add(new Procesor(p));
        }

        //this.procesory = inna.getProcesory().stream().collect(Collectors.toList());
        //list1.stream().collect(Collectors.toList());

        //Collections.copy(this.procesory, inna.getProcesory());
    }


    //*************** METODY POMOCNICZE ***************
    public void wyswietlZadania(){
        int i = 0;
        for (Procesor procesor : procesory){
            System.out.println("\n");
            System.out.println("procesor ["+ i + "] : ");
            i++;

            for(Zadanie zadanie : procesor.getSekwencja()){
                System.out.println(zadanie);
            }
        }
    }

    @Override
    public String toString() {
        return "Symulacja{" +
                //"procesory=" + procesory +
                ", liczbaProcesorow=" + liczbaProcesorow +
                ", max=" + Arrays.toString(max) +
                ", min=" + Arrays.toString(min) +
                ", zapytania=" + zapytania +
                '}';
    }

    //*************** GETTERY I SETTERY ***************


    public List<Procesor> getProcesory() {
        return procesory;
    }

    public void setProcesory(List<Procesor> procesory) {
        this.procesory = procesory;
    }

    public int getLiczbaProcesorow() {
        return liczbaProcesorow;
    }

    public void setLiczbaProcesorow(int liczbaProcesorow) {
        this.liczbaProcesorow = liczbaProcesorow;
    }

    public double[] getMax() {
        return max;
    }


    public void setMax(double[] max) {
        this.max = max;
    }

    public double[] getMin() {
        return min;
    }

    public void setMin(double[] min) {
        this.min = min;
    }

    public static int getZapytania() {
        return zapytania;
    }

    public static void setZapytania(int zapytania) {
        Symulacja.zapytania = zapytania;
    }

}
