import java.util.*;
public class ListaStud{
    Student[] tab = new Student[5];
    ArrayIterator it = new ArrayIterator(tab);
    public void wyswietl(){
        it.first();
        while(!it.isDone()){
            System.out.println(it.current());
            it.next();
        }
        System.out.println();
    }
    
    public void wypiszO(int nr,int ocena){
        it.first();
        while(!it.isDone()){
            Student temp =(Student)it.current();
            if(temp.getIndeks() == nr){
                temp.setOcena(ocena);
            }
            it.next();
        }
    }
    
    public double srednia(){
        double srednia = 0;
        int k = 0;
        for(it.first(); !it.isDone(); it.next()){
            Student temp = (Student)it.current();
            srednia += temp.getOcena();
            k++;
        }
        srednia /= k;
        return srednia;
    }
    
    public void dobra(){
        for(Student s: tab){
            if(s.getOcena() >= 4){
                System.out.println(s.toString());
            }
        }
    }
}
            
                