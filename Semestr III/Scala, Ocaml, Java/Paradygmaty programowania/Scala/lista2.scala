
/* zadanie numer jeden, rekurencja wykona się 4 razy dla 4,3,2,1 */
def evenR(n:Int):Boolean=if(n==0)true else oddR(n-1)
def oddR(n:Int):Boolean=if(n==0)false else evenR(n-1)

evenR (3)

/* ZAD 2*/

def fibonacci (n: Int): Int =
  n match {
  case 0 => 0
  case 1 => 1
  case _ => fibonacci(n-1) + fibonacci(n-2)
}

def fibonacci (n: Int): Int = fHelper(n, 0 ,1)
def fHelper (n: Int, f1: Int, f2: Int): Int = 
  n match {
  case 0 => f1
  case 1 => f2
  case _ => fHelper(n-1, f2, f2 + f1)
}

def f(n:Int) = {
  def fHelper(n:Int, f1:Int, f2:Int):Int =
    n match {
      case 0 => f1
      case 1 => f2
      case _ => fHelper(n - 1, f2, f1 + f2)
  }
  fHelper(n, 0, 1)
}

fibonacci (42)



/* ZAD 3*/

def root3(a: Double) = {
  def root3Helper(x: Double):Double =
    if (Math.abs(Math.pow(x, 3) - a) <= (1.0E-15  * Math.abs(a))) x
    else root3Helper(x + (a / Math.pow(x, 2) - x) / 3)
  root3Helper(if (a <= 1) a else a / 3)
}

root3 (10)



/* ZAD 4*/

val lista1 = List(-2, -1, 0, 1, 2)

val List(_, _, x, _, _) = lista1


val lista2 = List((1, 2), (0, 1))

val List((_, _), (x, _)) = lista2


/* ZAD 5*/

def initSegment (segment: List[Int], lista: List[Int]): Boolean =
  (segment, lista) match {
  case (Nil, _) => true
  case (_, Nil) => false
  case (_, _)   => if (segment.head == lista.head) initSegment(segment.tail, lista.tail) 
                   else false
}
  
  
initSegment(List(1,2,3,8), List(1,2,3,4,5,6))



/* ZAD 6*/

def replaceNth[A] (lista: List[A], indeks: Int, znak: A): List[A] =
  (lista, indeks) match{
  case (Nil,_)     => Nil
  case (h :: t, 0) => znak :: t
  case (h :: t, _) => h :: replaceNth ( t, indeks - 1, znak)
}
  
 replaceNth (List('o', 'l', 'a'), 1, 's')
  
  
  

