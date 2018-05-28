/*zadanie3 */

sealed trait BT[+A]
case object Empty extends BT[Nothing]
case class Node[+A](elem:A, left:BT[A], right:BT[A]) extends BT[A]
  
val tt = Node(1,
								Node(2,
											 Node(4,
															Empty, 
															Empty
											 ),
										   Empty
								),
								Node(3,
											 Node(5,
															Empty,
															Node(6,
																		 Empty,
																		 Empty
															)
											 ),
											 Empty
								)
				 );


def breadthBT[A] (binaryTree: BT[A]) = {
  def recTree[A](treeList: List[BT[A]]): List[A] = 
    treeList match {
    case Nil => Nil
    case Empty :: tl       => recTree (tl)
    case Node(v,l,r) :: tl => v :: recTree(tl ++ List(l,r))
  }
  recTree (List(binaryTree))
}

breadthBT (tt)

/*zadanie4 */

def wew[A] (binaryTree: BT[A]) = {
  def recGleb (glebokosc: Int, wezel: BT[A]): Int =
    wezel match {
      case Empty       => 0
      case Node(_,l,r) => glebokosc + recGleb (glebokosc + 1, l) + recGleb (glebokosc + 1, r)
  }
  recGleb(0, binaryTree)
}

wew (tt)


def zew[A] (binaryTree: BT[A]) = {
  def recGleb (glebokosc: Int, wezel: BT[A]): Int =
    wezel match {
      case Empty       => glebokosc
      case Node(_,l,r) => recGleb (glebokosc + 1, l) + recGleb (glebokosc + 1, r)
  }
  recGleb(0, binaryTree)
}

zew (tt)






/*zadanie5 */