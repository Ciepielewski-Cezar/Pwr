/**
 * Created by Czarek on 02.05.2017.
 */
public class Main {
    public static void main(String[] args) {

        Dane dane = new Dane(5);

        FIFO fcfs = new FIFO(dane);
        OPT opt = new OPT(dane);
        LRU lru = new LRU(dane);
        ALRU alru = new ALRU(dane);
        RAND rand = new RAND(dane);

        System.out.println("Liczba błędów w FIFO: " + fcfs.symulacja());
        System.out.println("Liczba błędów w OPT: " + opt.symulacja());
        System.out.println("Liczba błędów w LRU: " + lru.symulacja());
        System.out.println("Liczba błędów w ALRU: " + alru.symulacja());
        System.out.println("Liczba błędów w RAND: " + rand.symulacja());


    }
}

