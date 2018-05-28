
public class TestStos{
    public static void main(String[] args){
        StosLista stos = new StosLista();
        stos.push(4);
        stos.push(14);
        stos.push(78);
        stos.wyswietl();
        System.out.println();
        stos.pop();
        stos.wyswietl();
        System.out.println();
        stos.push(1);
        stos.wyswietl();
        System.out.println();
        System.out.println(stos.peek());
        System.out.println(stos.size());
        System.out.println(stos.isEmpty());
        stos.clear();
        System.out.println(stos.isEmpty());
        
    }
}