

public class TestStosTab{
public static void main(String[] args){
        StosTab stos = new StosTab(6);
        stos.push(4);
        stos.push(14);
        stos.push(78);
        stos.push(8);
        stos.push(40);
        stos.push(465);
        stos.wyswietl();
        System.out.println(stos.pop());
        System.out.println(stos.pop());
        System.out.println(stos.pop());
        System.out.println(stos.pop());
        System.out.println();
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
