
public class Test {
    public static void main(String[] args) {
        int dlugoscCiagu = 500;     //długość ciągu dla każdego procesu
        int iloscProcesow = 20;     //ilość procesów dla każdego algorytmu
        int iloscRamek = 60;        //ilość ramek przypadająca jednemu procesowi
        int maxStron = 300;         //maksymalna różnorodność stron dla ciągu

        ListaProcesow listaProcesow = new ListaProcesow(iloscProcesow, maxStron, dlugoscCiagu);

        Proporcjonalny proporcjonalny = new Proporcjonalny(listaProcesow.lista, iloscProcesow * iloscRamek);
        proporcjonalny.uruchom();
        System.out.println("Proporcjonalny: ");
        System.out.print(proporcjonalny.getBledy());
        System.out.println();
        System.out.println();


        Rowny rowny = new Rowny(listaProcesow.lista, iloscProcesow * iloscRamek);
        rowny.uruchom();
        System.out.println("Równy: ");
        System.out.print(rowny.getBledy());
        System.out.println();
        System.out.println();

        /*
        //zakresy stron dla poszczególnych procesów
        System.out.println(listaProcesow.lacznaIloscStron());
        System.out.println();
        //listaProcesow.lista[1].wyswietlCiag();
        System.out.println();
        System.out.println();
        for (int i = 0; i < listaProcesow.lista.length; i++) {
            listaProcesow.lista[i].wyswietlstrony();
            System.out.println();
            System.out.println();

        }
        */
    }
}
