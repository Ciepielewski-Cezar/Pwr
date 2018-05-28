import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class Tablica{
    List<Pracownik> listaPrac;
    int iloscMiejsc = 0;
    
    public Tablica(int iloscMiejsc){
        this.iloscMiejsc = iloscMiejsc;
        listaPrac = new ArrayList<Pracownik>();
    }
    
    
    public void dodajPracownika(Pracownik prac){
        if(listaPrac.size() < iloscMiejsc){
            listaPrac.add(prac);
        }
        System.out.println("Rozmiar listy to: "+listaPrac.size());
    }
    
    public void usun(){
        for(int i = 0; i<listaPrac.size(); i++)
        listaPrac.remove(0);
    }
    
    public String usunPracownika(String n){
        String czy = "";
        for(int i = 0; i<listaPrac.size(); i++){
            if (listaPrac.get(i).getNazwisko().equals(n)){
                listaPrac.remove(i);
                czy = "Usunięto pracownika o nazwisku " + n;
                return czy;
            }
        }
        czy = "Nie ma takiego pracownika.";
        return czy;
    } 

    
    public String znajdzPrac(String n){
        for(Pracownik p: listaPrac){
            if (p.getNazwisko().equals(n)){
                return "Pracownik jest na liście.";
            }
        }
        return "Nie ma takiego pracownika na liście.";
    }
    
    public String czyJest(Pracownik p){
        if(p instanceof Urzednik){
            return "Pracownik jest urzędnikiem";
        }
        else return "Pracownik jest robotnikiem";
    }
    
    public void sumaPrac(){
        int sumaRob = 0;
        int sumaUrz = 0;
        for(int i = 0; i<listaPrac.size(); i++){
            if(listaPrac.get(i) instanceof Robotnik){
                sumaRob++;
            }
            else if(listaPrac.get(i) instanceof Urzednik){
                sumaUrz++;
            }
        }
        System.out.println("Liczba robotników: " + sumaRob + "\tLiczba urzędników: " + sumaUrz);
        System.out.println("\n");
    }
   
    public void sumaWyplat(){
        double sumaRob = 0;
        double sumaUrz = 0;
        
        for(int i = 0; i<listaPrac.size(); i++){
            if(listaPrac.get(i) instanceof Robotnik){
                sumaRob += listaPrac.get(i).getWyplata();
            }
            else if(listaPrac.get(i) instanceof Urzednik){
                sumaUrz += listaPrac.get(i).getWyplata();
            }
        }
        System.out.println("Suma wypłaty robotników: " + sumaRob + "\tSuma wypłaty urzędników: " + sumaUrz);
        System.out.println("\n");
    }
    
    public void listaPracownikow(){
        Iterator<Pracownik> listaPracIterator = listaPrac.iterator();
        System.out.println("Lista pracowników: ");
        int i = 0;
        while(listaPracIterator.hasNext()){
            System.out.print(i + ". ");
            System.out.print(listaPracIterator.next());
            System.out.println();
            i++;
        }
        System.out.println("\n");
    }
    
    public void listaRobotnikow(){
        System.out.println("Lista robotników: ");
        for(int i = 0; i<listaPrac.size(); i++){
            if(listaPrac.get(i) instanceof Robotnik){
                System.out.print(i + ". ");
                System.out.print(listaPrac.get(i).toString());
                System.out.println();
            }
        }
        System.out.println("\n");
    }
    
    public void listaUrzednikow(){
        System.out.println("Lista urzędników: ");
        for(int i = 0; i<listaPrac.size(); i++){
            if(listaPrac.get(i) instanceof Urzednik){
                System.out.print(i + ". ");
                System.out.print(listaPrac.get(i).toString());
                System.out.println();
            }
        }
        System.out.println("\n");
    }
    
    public void listaPlac(){
        System.out.println("Lista płac: ");
        for(int i = 0; i<listaPrac.size(); i++){
            System.out.print(i + ". ");
            System.out.print(listaPrac.get(i).toString());
            System.out.println(" wypłata: " + listaPrac.get(i).getWyplata());
        }
        System.out.println("\n");
    }
}

/*
 *     public void listaPracownikow(){
        Iterator<Pracownik> listaPracIterator = listaPrac.iterator();
        System.out.println("Lista pracowników: ");
        for(int i = 0; i<listaPrac.size(); i++){
            System.out.print(i + ". ");
            System.out.print(listaPrac.get(i).toString());
            System.out.println();
        }
        System.out.println("\n");
    }
    */
   