

public class Tablica{
    int[] tab;
    int size;
    int zakres;
    
    int porownaniaTL = 0;
    int porownaniaNL = 0;
    int trafioneL = 0;
    int chybioneL = 0;
    
    int porownaniaTB = 0;
    int porownaniaNB = 0;
    int trafioneB = 0;
    int chybioneB = 0;
    
    public Tablica(int rozmiar, int zakres){
        size = rozmiar;
        this.zakres = zakres;
        this.tab = new int[size];
    }
    
    public void wypelnij(){
        for(int i = 0; i < size; i++){
            tab[i] = (int)(Math.random() * zakres);
        }
    }
    
    public int liniowe(int x){
         int p = 0;
         for(int i = 0; i < size; i++){
             p++;
             if( tab[i] == x){
                 trafioneL++;
                 porownaniaTL += p;
                 return tab[i];
             }
         }
         chybioneL++;
         porownaniaNL += p;
         return -1;
    }
    
    public int binarne(int x){
        int a = 0;
        int b = size - 1;
        int c = 0;
        int p = 0;
        
        while(a<=b){
            c=(a + b)/2;
            if(tab[c] == x){
                p++;
                trafioneB++;
                porownaniaTB += p;
                return c;
            }
            if(x<tab[c]){
                b=c-1;
                p++;
            }
            else{
                a=c + 1;
                p++;
            }
        }
        chybioneB++;
        porownaniaNB += p;
        return -1;
    }
    
    public int binarne2(int x){
        int a = tab[0];
        int b = tab[size - 1];
        int c = 0;
        boolean koniec = false;
        int p = 0;
        do{
            c = (a+b)/2;
            if (x == tab[c]){
                trafioneB++;
                porownaniaTB += p;
                
                koniec = true;
                return c;
            }
            else if ( x > tab[c]){
                a = c + 1;
                p++;
            }
            else{
                b = c;
                p++;
            }
            }while (!koniec && a < b);
            if(koniec){
                return c;
            }
            else{
                chybioneB++;
                porownaniaNB += p;
                System.out.println("Nie znaleziono liczby.");
                return -1;
            }
    }
    
    public void shell(){
        int increment = tab.length / 2;
	while (increment > 0) {
		for (int i = increment; i < tab.length; i++) {
			int j = i;
			int temp = tab[i];
			while (j >= increment && tab[j - increment] > temp) {
				tab[j] = tab[j - increment];
				j = j - increment;
			}
			tab[j] = temp;
		}
		if (increment == 2) {
			increment = 1;
		} else {
			increment *= (5.0 / 11);
		}
	}
    }

        
    
    public void wyswietl(){
        for(int i = 0; i < size; i++){
            System.out.println(tab[i]);
        }
    }
}
    
        