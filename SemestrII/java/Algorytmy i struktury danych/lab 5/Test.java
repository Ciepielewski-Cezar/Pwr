
public class Test{
    public static void main(String[] args){
        TabQueue tab = new TabQueue(3);
        
        tab.enqueue("kosiarka");
        tab.enqueue("ciągnik");
        tab.enqueue("łopata");
        tab.wyswietl();
        tab.dequeue();
        tab.wyswietl();
        System.out.println(tab.size);
        System.out.println(tab.isEmpty());
    }
}
