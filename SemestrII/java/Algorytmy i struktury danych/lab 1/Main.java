import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        Lista lista = new Lista();
        lista.wczytaj();
        lista.wyswietlPara(2);
        lista.wartosc();
        lista.zmianaCeny("komputer", 8000);
        lista.wyswietl();
        
        lista.zapisz();
    }
}
        