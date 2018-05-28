
public class Graf{
   
    
   public void makeSet(Element x){
       x.parent = x;
       x.height = 0;
   }
   
   public void union(Element x, Element y){
       link(find(x), find(y));
    }
    
   public void link(Element x, Element y){
        if (x.height > y.height){
            y.parent=x;
        } else {
            x.parent=y;
            
            if (x.height == y.height){
                y.height++;
            }
        }
   }
   
   public Element find(Element x){
        if (x != x.parent){
            x.parent = find(x.parent);
        }
        return x.parent;
   }
    
   public void wyswietl(Element x){
       while(x.parent != null){
           System.out.println(x.value);
           if(x == x.parent){
               break;
           } else{
               //wyswietl(x.parent);
               x = x.parent;
           }
       }
       System.out.println();
    }
       
}
       