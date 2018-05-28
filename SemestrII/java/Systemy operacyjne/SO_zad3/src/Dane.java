import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Created by Czarek on 02.05.2017.
 */
public class Dane {
    int[] ramki;                //iloć ramek
    private int strony = 300;   //ilość stron, domyślnie 300 z wersji testowej z eportalu
    int[] ciag;                 //ciąg odwołań z eportalu, pobierany z pliku w folderze src ("plikPrzecinki.txt"). Plik "Plik.txt" zawiera dane bez przecinków.
    private long size;          //ilość stron w ciągu odwołań

    //konstruktor dla ciągu testowego z eportalu
    public Dane(int iloscRamek) {
        this.ramki = new int[iloscRamek];
        for (int i = 0; i < ramki.length; i++)
            ramki[i] = Integer.MAX_VALUE;   //domyślnie ustawiamy maksymalną wartość na ramkach
        wczytaj();                          //wczytujemy ciąg testowy z pluku
        this.size = ciag.length;
    }

    //metoda czytająca ciąg testowy z pliku .txt do ciag
    private void wczytaj() {
        try {
            File file = new File("plikprzecinki.txt");
            byte[] bytes = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytes);
            fis.close();

            String[] valueStr = new String(bytes).trim().split("\\s+");
            ciag = new int[valueStr.length];

            for (int i = 0; i < valueStr.length; i++)
                ciag[i] = Integer.parseInt(valueStr[i].replaceAll(",", ""));    //usuwa przecinki
            //System.out.println(Arrays.asList(ciag));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //**********GETTERY I SETTERY**********
    public long getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[] getRamki() {
        return ramki;
    }

    public void setRamki(int[] ramki) {
        this.ramki = ramki;
    }

    public int getStrony() {
        return strony;
    }

    public void setStrony(int strony) {
        this.strony = strony;
    }

    public int[] getCiag() {
        return ciag;
    }

    public void setCiag(int[] ciag) {
        this.ciag = ciag;
    }
}
