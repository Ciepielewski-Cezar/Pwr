//Zad 2
//a)

def swap[A] (tab: Array[A]) (i: Int) (j: Int) = {
  val tmp = tab(i)
  tab(i) = tab(j)
  tab(j) = tmp
}


def choose_pivot[A] (tab: Array[A]) (m: Int) (n: Int) = tab((m+n)/2)




//b) nie działa

def partition (tab: Array[Int]) (l: Int) (r: Int) = {
  var i = l
  var j = r
  val pivot = choose_pivot (tab)(l)(r)
  while (i <= j){
    while (tab(i) < pivot) i += 1 
    while (pivot < tab(j)) j -= 1 
    if (i <= j){
      swap(tab)(i)(j)
      i += 1
      j -= 1
      }
    }
  (i, j)
  }



//Zad 3
public class IsEqual {
    static boolean isEqual1(int m, int n) {
        return m == n;
    }

    static boolean isEqual2(Integer m, Integer n) {
        return m == n;
    }

    public static void main(String[] args) {
        System.out.println(isEqual1(500, 500));  //true
        System.out.println(isEqual2(500, 500));  //false
    }
}



//Zad 4

public class Porównanie{
  public static void main(String[] args){
    String s1 = "foo";
    String s2 = "foo";
    
    System.out.println(s1 == s2);      //true
    System.out.println(s1.equals(s2)); //true
    
    String s3 = new String("foo");
    
    System.out.println(s1 == s3);       //false
    System.out.println(s1.equals(s3));  //true
   }
}
