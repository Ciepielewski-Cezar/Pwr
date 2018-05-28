public class Student{
    String imie;
    String nazwisko;
    int indeks;
    int ocena;
    
    public Student(String i, String n, int in, int o){
        imie = i;
        nazwisko = n;
        indeks = in;
        ocena = o;
    }
    public int getIndeks(){
        return indeks;
    }
    public void setOcena(int x){
        ocena = x;
    }
    public int getOcena(){
        return ocena;
    }
    public String toString(){
        return imie+" "+nazwisko+" "+indeks+" "+ocena;
    }
}