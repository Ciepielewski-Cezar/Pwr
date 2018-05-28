//Zad 1

def lrepeat[A](llista: Stream[A]) (k: Int): Stream[A] = {
  def helper[A](result: Stream[A], iter: Int): Stream[A] =
    (result, iter) match {
    case (Stream.Empty, _) => Stream.Empty
    case ((_#::t), 0) => helper(t, k)
    case ((h#::_), _) => h #:: helper(result, iter - 1)
  }
  helper (llista, k)
}
val s1 = Stream(1,2,3,4,5)

val x = lrepeat (s1) (3)

x.toList
res2: List[Int] = List(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5)



//Zad 2

val lfib = {
  def fib(a: Int, b: Int): Stream[Int] =
    a #:: fib(b, b+a)
    fib(1,1)
}

lfib.toList


//Zad 3
sealed trait lBT[+A]
case object LEmpty  extends lBT[Nothing]
case class LNode[+A](elem:A, left:()=>lBT[A], right:()=>lBT[A]) extends lBT[A]

//a)

def lTree(n: Int): lBT[Int] =
  LNode(n, () => lTree(2*n), () => lTree(2*n+1))
  
  
  
//b)
  
def breadthlBT[A] (lBtree: lBT[A]) = {
  def recTree (kolejka: List[lBT[A]]): Stream[A] = 
    kolejka match {
      case Nil => Stream.Empty
      case (LEmpty::tl) => recTree(tl)
      case (LNode(v,l,r) :: tl) => v #:: recTree(tl ++ List(l(), r()))
  }
  recTree(List(lBtree))
}
  