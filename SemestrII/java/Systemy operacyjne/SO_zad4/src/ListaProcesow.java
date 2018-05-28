
public class ListaProcesow {
    Proces[] lista; //nasze procesy

    public ListaProcesow(int iloscProcesow, int maxStrona, int dlugoscCiagu) {
        lista = new Proces[iloscProcesow];
        int strona = 1;

        for (int i = 0; i < lista.length; i++) {
            int[] strony = new int[(int) (Math.random() * maxStrona + 5)];    //generujemy ilość stron dla procesu

            for (int j = 0; j < strony.length; j++) {
                strony[j] = strona++;   //strony dla danego procesu od  do dlugosci stron
            }
            lista[i] = new Proces(strony, dlugoscCiagu);
        }
    }


    //**********METODY POMOCNICZE**********

    //łączna ilość (i też długość) stron dla wszystkich procesów
    public int lacznaIloscStron() {
        int lacznaIloscStron = 0;
        for (int i = 0; i < lista.length; i++) {
            lacznaIloscStron += lista[i].getIloscStron();
        }
        return lacznaIloscStron;
    }
}
