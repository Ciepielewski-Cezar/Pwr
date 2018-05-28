import com.sun.nio.sctp.PeerAddressChangeNotification;

import java.util.TreeSet;
import java.util.*;

public class Tablica {
    TreeSet<Pracownik> listaPrac;
    int iloscMiejsc = 0;


    public Tablica(int iloscMiejsc) {
        this.iloscMiejsc = iloscMiejsc;
        listaPrac = new TreeSet<Pracownik>();
    }


    public void dodajPracownika(Pracownik prac) {
        if (listaPrac.size() < iloscMiejsc) {
            listaPrac.add(prac);
        }
        System.out.println("Rozmiar listy to: " + listaPrac.size());
    }

    public String usunPracownika(String nazw) {
        String czy = "";
        Iterator<Pracownik> it = listaPrac.iterator();

        while (it.hasNext()) {
            if (it.next().getNazwisko().equals(nazw)) {
                it.remove();
                czy = "Usunięto pracownika o nazwisku " + nazw;
                return czy;
            }
        }
        czy = "Nie ma takiego pracownika.";
        return czy;
    }


    public String znajdzPrac(String n) {
        for (Pracownik p : listaPrac) {
            if (p.getNazwisko().equals(n)) {
                return "Pracownik jest na liście.";
            }
        }
        return "Nie ma takiego pracownika na liście.";
    }

    public String czyJest(Pracownik p) {
        if (p instanceof Urzednik) {
            return "Pracownik jest urzędnikiem";
        } else return "Pracownik jest robotnikiem";
    }

    public void sumaPrac() {
        int sumaRob = 0;
        int sumaUrz = 0;
        for (Pracownik p : listaPrac) {
            if (p instanceof Robotnik) {
                sumaRob++;
            } else if (p instanceof Urzednik) {
                sumaUrz++;
            }
        }
        System.out.println("Liczba robotników: " + sumaRob + "\tLiczba urzędników: " + sumaUrz);
        System.out.println("\n");
    }

    public void sumaWyplat() {
        double sumaRob = 0;
        double sumaUrz = 0;

        for (Pracownik p : listaPrac) {
            if (p instanceof Robotnik) {
                sumaRob += p.getWyplata();
            } else if (p instanceof Urzednik) {
                sumaUrz += p.getWyplata();
            }
        }
        System.out.println("Suma wypłaty robotników: " + sumaRob + "\tSuma wypłaty urzędników: " + sumaUrz);
        System.out.println("\n");
    }

    public void listaPracownikow() {
        System.out.println("Lista pracowników: ");
        int numerowanie = 0;
        Iterator<Pracownik> listaPracIterator = listaPrac.iterator();
        
        while (listaPracIterator.hasNext()) {
            System.out.print(numerowanie++ + ". ");
            System.out.print(listaPracIterator.next());
            System.out.println();
        }
        System.out.println("\n");
    }

    public void listaRobotnikow() {
        System.out.println("Lista robotników: ");
        int numerowanie = 0;

        for (Pracownik p : listaPrac) {
            if (p instanceof Robotnik) {
                System.out.print(numerowanie++ + ". ");
                System.out.print(p.toString());
                System.out.println();
            }
        }
        System.out.println("\n");
    }

    public void listaUrzednikow() {
        System.out.println("Lista urzędników: ");
        int numerowanie = 0;

        for (Iterator<Pracownik> itr = listaPrac.iterator(); itr.hasNext(); ) {
            Pracownik prac = itr.next();
            if (prac instanceof Urzednik) {
                System.out.print(numerowanie++ + ". ");
                System.out.print(prac.toString());
                System.out.println();
            }
        }
        System.out.println("\n");
    }

    public void listaPlac() {
        System.out.println("Lista płac: ");
        int numerowanie = 0;
        Iterator<Pracownik> itr = listaPrac.iterator();

        while (itr.hasNext()) {
            Pracownik prac = itr.next();
            System.out.print(numerowanie++ + ". ");
            System.out.print(prac.toString());
            System.out.println(" wypłata: " + prac.getWyplata());
        }
        System.out.println("\n");
    }
}

/*
 *     while (itr.hasNext()) {
            Pracownik prac = itr.next();
            System.out.print(numerowanie++ + ". ");
            System.out.print(prac.toString());
            System.out.println(" wypłata: " + prac.getWyplata());
        }
        
        while (itr.hasNext()) {
            System.out.print(numerowanie++ + ". ");
            System.out.print(itr.next().toString());
            System.out.println(" wypłata: " + itr.next().getWyplata());
        }
    */
   