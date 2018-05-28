public class Element{
       int value;
       Element parent;
       int height;
       
       public Element(int v){
           value = v;
           parent = null;
           height = 0;
       }
        
       public Element(int v,Element p){
           value = v;
           parent = p;
           height = p.height + 1;
       }
       
       public int getValue(){
           return value;
       }
        
       public Element getParent(){
           return parent;
       }
       
       public void setValue(int v){
           value = v;
       }
       
       public void setParent(Element p){
           parent = p;
       }
    }