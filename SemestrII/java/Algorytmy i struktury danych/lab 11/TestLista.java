public class TestLista{
    public static void main (String[] args){
        Lista lista = new Lista(29);
        lista.put("ala");
        lista.put("Hania");
        lista.put("kot");
        lista.put("Michal");
        lista.put("pies");
        lista.put("andzej");
        lista.put("swinia");
        lista.put("krowa");
        lista.put("samochod");
        lista.put("motocykl");
        lista.put("dom");
        
        lista.get("kot");
        System.out.println();
        System.out.println("czy ala należy (należy): ");
        System.out.println(lista.containsKey("ala"));
        System.out.println();
        System.out.println("czy fgv należy (nie należy): ");
        System.out.println(lista.containsKey("fgv"));
        System.out.println();
        lista.dump();
        
    }
}