/* ZAD 1*/


/* ZAD 2*/

def curry3[A,B,C,D] (f: (A, B, C) => D) =
  (x: A) => (y: B) => (z: C) => f(x, y, z)
  /* curry3: [A, B, C, D](f: (A, B, C) => D)A => (B => (C => D)) */
  
def add(x: Int, y: Int, z: Int) = x + y + z
/* add: (x: Int, y: Int, z: Int)Int */
curry3(add)(1)(2)(3)
  
  
def uncurry3[A,B,C,D] (f: A => B => C => D) =
  (x: A, y: B, z: C) => f(x)(y)(z)

def add(x: Int)(y: Int)(z: Int) = x + y + z
/* add: (x: Int)(y: Int)(z: Int)Int */
uncurry3(add)(1, 2, 3)

/* ZAD 3*/

def sumProd(xs:List[Int]):(Int, Int) =
  xs match {
    case h::t => {val (s,p) = sumProd(t)
                  (h+s, h*p)
                 }
    case Nil => (0, 1)
}
 
let sumProd lista = List.fold_left  (fun (s,m) el -> (s + el, m * el)) (0,1) lista
  
def sumProd(list:List[Int]) = (list foldLeft (0,1)) ((s, m) => (acc._1 + h, acc._2 * h))

def sumProd(lista:List[Int]) = (lista foldLeft (0,1)) ((acc, next) => (acc._1 + next, acc._2 * next))

  
sumProd (List(1,3,5))