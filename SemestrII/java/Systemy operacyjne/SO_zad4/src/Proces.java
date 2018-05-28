
public class Proces {
    int[] ciag;                     //ciąg odwołań danego procesu, długość ustalana w test
    int[] strony;                   //wygenerowane ciag stron dla procesu po kolei
    int ramki;                      //ilość ramek dla procesu
    private int iloscStron;         //ilość stron w strony[]
    private LRU lru;


    public Proces(int[] zewStrony, int dlugoscCiagu) {
        strony = zewStrony;              // strony generowane z liście procesów
        iloscStron = strony.length;      // ilość stron w strony[] która jest losowa
        ciag = new int[dlugoscCiagu];    //długość ustawiana w teście


        //generujemy losowe strony do ciągu odwołań z dostępnych stron
        for (int i = 0; i < dlugoscCiagu; i++)
            ciag[i] = strony[(int) (Math.random() * iloscStron)];

    }

    //włącza LRU na tym procesie
    public void symuluj() {
        lru = new LRU(ciag, ramki);
        lru.symulacja();
    }


    //**********METODY POMOCNICZE**********

    public int[] getCiag() {
        return ciag;
    }

    public void setCiag(int[] ciag) {
        this.ciag = ciag;
    }

    public int[] getStrony() {
        return strony;
    }

    public void setStrony(int[] strony) {
        this.strony = strony;
    }

    public int getRamki() {
        return ramki;
    }

    public void setRamki(int ramki) {
        this.ramki = ramki;
    }

    public int getIloscStron() {
        return iloscStron;
    }

    public void setIloscStron(int iloscStron) {
        this.iloscStron = iloscStron;
    }

    public int getBledy() {
        return lru.getBledy();
    }


    public void wyswietlCiag() {
        int licznik = 1;
        for (int i = 0; i < ciag.length; i++) {
            System.out.println(licznik + ". : " + ciag[i]);
            licznik++;
        }
    }

    public void wyswietlstrony() {
        int licznik = 1;
        for (int i = 0; i < strony.length; i++) {
            System.out.println(licznik + ". : " + strony[i]);
            licznik++;
        }
    }
}
