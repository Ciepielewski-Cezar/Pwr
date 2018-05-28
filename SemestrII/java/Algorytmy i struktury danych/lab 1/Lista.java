import java.util.ArrayList;
import java.io.*;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;

public class Lista{
    BufferedReader br = null;
    List<Towar> lista = new ArrayList<Towar>();
    
    public void wczytaj(){
        try{
            br = new BufferedReader(new FileReader("plikPrzyklad.txt"));
            String line = "";
            br.readLine();
            line = br.readLine();
            line.trim();
            while(line != null && !line.equals("")){
                String[] podziel = line.split(" ");
                String nazwa =(podziel[0]);
                double ilosc = Double.parseDouble(podziel[1]);
                double cena = Double.parseDouble(podziel[2]);
                double wartosc = Double.parseDouble(podziel[3]);
            
                Towar towar = new Towar(nazwa, ilosc, cena, wartosc);
                lista.add(towar);
                line = br.readLine();
                System.out.println(towar);
                if(line != null) line.trim();
            }
        }catch (Exception e){ System.out.println("błąd");}
         System.out.println();
    }
    
    public void zmianaCeny(String n, double c){
        for(Towar towar: lista){
            if ((towar.getNazwa()).equals(n)){
                towar.setCena(c);
                towar.setWartosc(towar.getCena()*towar.getIlosc());
            }
        }
    }
    
    public void wyswietl(){
        for(Iterator<Towar> it = lista.iterator(); it.hasNext();){
            Towar towar = it.next();
            System.out.println(towar);
        }
        System.out.println();
    }
    
    public void wyswietlPara(double i){
        for(Towar towar: lista){
            if (towar.getIlosc()<i){
                System.out.println(towar);
            }
        }
        System.out.println();
    }
    
    public void wartosc(){
        double suma = 0;
        for(Towar towar: lista){
            suma += towar.getIlosc() * towar.getCena();
        }
        System.out.println(suma);
        System.out.println();
    }
    
    public void zapisz()throws IOException {
        BufferedWriter bw = null;
        
        try{
            bw = new BufferedWriter(new FileWriter("nowy2.txt"));
            String line = "";
            Iterator<Towar> it = lista.iterator();
            while(it.hasNext()){
                Towar towar = it.next();
                line = towar.toString();
                bw.write(line);
                bw.newLine();
            }
        }catch(Exception e){ System.out.println("Inny błąd");}
        finally {
			if(bw != null) bw.close();
        }
    }
}
