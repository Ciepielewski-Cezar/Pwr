public class Main{
    public static void main(String[] args){
        ListaStud t = new ListaStud();
        t.tab[0] = new Student("Michał", "Marcinkowski", 204915, 4);
        t.tab[1] = new Student("Darek", "Tuk", 154658, 2);
        t.tab[2] = new Student("Lukasz", "Pederowicz", 175278, 3);
        t.tab[3] = new Student("Marek", "Cichak", 238012, 2);
        t.tab[4] = new Student("Paweł", "Kołodziej", 696423, 5);
        
        t.wyswietl();
        t.wypiszO(204915, 6);
        t.wyswietl();
        System.out.println(t.srednia());
        t.dobra();
    }
}