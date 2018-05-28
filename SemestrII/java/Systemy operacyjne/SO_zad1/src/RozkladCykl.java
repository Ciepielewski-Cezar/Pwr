import java.util.Arrays;

import static java.lang.Math.*;

public class RozkladCykl {
    int[] wejscieCykli;

    public RozkladCykl(int k) {
        wejscieCykli = new int[k];
    }

    public int losuj() {
        int mu = 0;
        double sigma = 1.5; // 0.5 50-70 na
        //duży rozstrzał 1.5 5 1
        //duży dobry rozstrzał 1.5 3 2.5
        //bardzo dobry 1.44 3 2.35
        double x = ((random() * 3) + 0.1);
        double r = (exp(-pow(log(x) - mu, 2)) / (2 * pow(sigma, 2)) / (x * sigma * sqrt(2 * PI))) * 100000;
        int liczba = (int) r;

        /* sigma 0.5:
         * (random() * 4) + 10
         * r*100000
         * random 3,10 38-41
         * random 4,10 29-34
         * random 5,10 24-29
         */
        return liczba;
    }

    public void sort() {
        int temp = 0;
        for (int i = 0; i < wejscieCykli.length - 1; i++) {
            for (int j = i + 1; j < wejscieCykli.length; j++) {
                if (wejscieCykli[i] > wejscieCykli[j]) {
                    temp = wejscieCykli[i];
                    wejscieCykli[i] = wejscieCykli[j];
                    wejscieCykli[j] = temp;
                }
            }
        }
    }
    //TESTOWANIE LOSOWOŚCI DODANIA CYKLI
    public void uzupelnij() {
        for (int i = 0; i < wejscieCykli.length; i++) {
            //wejscieCykli[i] = Integer.MAX_VALUE;
            wejscieCykli[i] = losuj();
        }
        sort();
    }

    public void wyswietl() {
        for (int i = 0; i < wejscieCykli.length; i++) {
            System.out.println("wejście[" + i + "]: " + wejscieCykli[i]);
        }
    }
}