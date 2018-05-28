(*ZAD 1*)
(*ZAD 2*)
let funkcja argument = funkcja argument;;

let f f2  = List.hd []

(*ZAD 3*)

type 'a bt = Empty | Node of 'a * 'a bt * 'a bt;;

let tt = Node(1,
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
				 				);;
				
				let t = Node(1,Node(2,Empty,Node(3,Empty,Empty)),Empty);;
				
let breadthBT binaryTree =
	let rec recTree = function
		[] -> []
		| Empty :: tl         -> recTree tl
		| Node(v, l, r) :: tl -> v :: recTree (tl @ [l; r])
	in recTree [binaryTree]
 
breadthBT tt



(*ZAD 4*)
let wew binaryTree =
	let rec recGleb glebokosc = function
		| Empty -> 0
		| Node(_,l,r) -> glebokosc + recGleb (glebokosc + 1) l  + recGleb (glebokosc + 1) r
	in recGleb 0 binaryTree;;

wew tt;;


let zew binaryTree =
	let rec recGleb glebokosc = function
		| Empty -> glebokosc
		| Node(_,l,r) -> recGleb (glebokosc + 1) l  + recGleb (glebokosc + 1) r
	in recGleb 0 binaryTree;;

zew tt;;


(*ZAD 5*)

type 'a graph = Graph of ('a -> 'a list);;


let g = Graph
(function
0 -> [3]
| 1 -> [0;2;4]
| 2 -> [1]
| 3 -> []
| 4 -> [0;2]
| n -> failwith ("Graph g: node "^string_of_int n^" doesn't exist")
);;
