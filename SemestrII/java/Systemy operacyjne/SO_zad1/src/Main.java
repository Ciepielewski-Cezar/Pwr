public class Main {
    public static void main(String[] args) {

        Cykl c1 = new Cykl(100);
        c1.uzupelnij();

        //wyświetlanie odbywa się w fcfs.symulacja ponieważ procesy wyrównują się by zacząć od zera
        //c1.wyswietl();
        System.out.println("Rozkład dziesiętny długości procesów: ");
        c1.tabela();
        c1.srednia();
        c1.suma();
        System.out.println("\n");


        FCFS fcfs = new FCFS(c1);
        SJF sjf = new SJF(c1);
        RR rr = new RR(c1);
        SJFW sjfw = new SJFW(c1);

        System.out.println("wyświetlanie cyklu po sortowaniu wg czasu wstawienia i wyrównaniu: ");
        //wyrównanie to przesunięcie wszystkich czasów wstawienia procesów tak by proces o najmniejszym czasie
        //wstawienia posiadał czas wstawienia 0

        System.out.println(fcfs.symulacja());

        System.out.println(sjf.symulacja());

        System.out.println(sjfw.symulacja());

        System.out.println(rr.symulacja());



    }
}