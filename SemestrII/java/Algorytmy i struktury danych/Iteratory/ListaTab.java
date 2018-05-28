public class ListaTab{
    Student[] tab;
    ArrayIterator it = new ArrayIterator(tab);
    
    public void wyswietl(){
        it.first();
        while(!it.isDone()){
            System.out.println(it.current());
            it.next();
        }
    }
    
    public void wyswietl2(){
        for(it.first();it.isDone();it.next()){
            System.out.println((Student)it.current());
        }
    }
}