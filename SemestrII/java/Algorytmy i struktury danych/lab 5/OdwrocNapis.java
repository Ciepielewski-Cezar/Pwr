import java.util.Scanner;

public class OdwrocNapis{
    StosTab stos = new StosTab(20);

    
    public void wczytaj(){
        String zdanie = "";
        String nowe = "";
        
        Scanner czytaj = new Scanner(System.in);
        System.out.println("Podaj zdanie: ");
        zdanie = czytaj.nextLine();

        while(!zdanie.isEmpty()){
            for(int i = 0; i<zdanie.length(); i++){
                stos.push(zdanie.charAt(i));
            }
        
            for(int i = 0; i<zdanie.length(); i++){
                nowe += stos.pop();
            }
            System.out.println(nowe);
            zdanie = "";
            nowe = "";
            System.out.println("Podaj zdanie: ");
            zdanie = czytaj.nextLine();
        }
        czytaj.close();
        System.out.println("koniec.");
    }
}
