
public class TestLista{
    public static void main(String[] args){
        ListaQueue tab = new ListaQueue();
        tab.enqueue("kosiarka");
        tab.enqueue("ciągnik");
        tab.enqueue("łopata");
        tab.wyswietl();
        tab.dequeue();
        tab.wyswietl();
        System.out.println(tab.size());
        System.out.println(tab.isEmpty());
    }
}