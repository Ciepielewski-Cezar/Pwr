def flatten[A](listaList:List[List[A]]):List[A] = 
  if (listaList == Nil) Nil
  else listaList.head ++ flatten(listaList.tail)
  
  
def count[A](obiekt:A, lista:List[A]):Int =
  if (lista == Nil) 0
  else (if (lista.head == obiekt) 1 else 0) + count(obiekt, lista.tail)
  
  count('a',['a';'l';'a'])
  
  
def replicate[A](obiekt:A, ilosc:Int):List[A] =
  if (ilosc < 0) throw new Exception ("ujemny argument")
  else if (ilosc == 0) Nil
  else  obiekt :: replicate(obiekt, ilosc - 1)
  
  replicate("la",3)
  
def sqrList(lista:List[Int]):List[Int] = 
  if (lista == Nil) Nil
  else (lista.head * lista.head) :: sqrList(lista.tail)
  
def palindrome[A](lista:List[A]) = 
  lista == lista.reverse
  
def listLength[A](lista:List[A]):Int = 
  if (lista == Nil) 0
  else (listLength(lista.tail)) + 1
  
  
  
  
def f(list:Any):Boolean = {
    list match{
      case (a::b)::c => true
      case _ => false
      }
}



def f(list:Any):Boolean = {
    list match{
      case a::b::c => true
      case _ => false
    }
  }
  

 f(1::2::3::4::Nil::Nil)
  