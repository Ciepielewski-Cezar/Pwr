(*ZAD 1 TO NIE*)

let f1 x = x 2 2;;

val f1 : (int -> int -> 'a) -> 'a = <fun>


let f2 x y z = x ( y ^ z );;
(* operator ^ łączy stringi (konkatenacja łańcuchów) *)

val f2 : (string -> 'a) -> string -> string -> 'a = <fun>



(*ZAD 2 TO NIE *)

let curry3 f x y z = f(x, y, z);;
(* val curry3 : ('a * 'b * 'c -> 'd) -> 'a -> 'b -> 'c -> 'd = <fun> *)

let add(x,y,z) = x + y + z;;
curry3 add 1 2 3;;
(* val add : int * int * int -> int = <fun> *)


let uncurry3 f(x, y, z) = f x y z;;
(* val uncurry3 : ('a -> 'b -> 'c -> 'd) -> 'a * 'b * 'c -> 'd = <fun> *)

let add x y z = x + y + z;;
uncurry3 add (2,3,4);;
(* val add : int -> int -> int -> int = <fun> *)





(*ZAD 3 *)

let rec sumProd xs =
	match xs with
	| h::t -> let (s,p) = sumProd t
	 in (h+s, h*p)
  	| [] -> (0,1);;

let sumProd lista = List.fold_left  (+)( * ) (0,1) lista
(*Pierwszym parametrem jest procedura kumulująca wynik. Ma ona dwa argumenty: dotychczas obliczony wynik i kolejny element listy do przetworzenia.
 Drugi argument to wynik dla pustej listy, a trzeci to lista do przetworzenia. *)

let sumProd lista = List.fold_left  (fun (s,m) el -> (s + el, m * el)) (0,1) lista

sumProd [1;3;5];;


(*ZAD 4 *)
(*a*)
let rec quicksort = function
	|  []  -> []
	|  [x] -> [x]
	|  xs  -> let small = List.filter (fun y -> y < List.hd xs ) xs 
						and large = List.filter (fun y -> y >= List.hd xs ) xs
						in quicksort small @ quicksort large;;

(*dobra*)
let rec quicksort = function             
	| []     -> []
	| [x]    -> [x]        
	| x::xs  -> let small = List.filter (fun y -> y < x ) xs                    
						and large = List.filter (fun y -> y >= x ) xs                    
						in quicksort small @ (x :: quicksort large);;

(* filter p l returns all the elements of the list l that satisfy the predicate p. The order of the elements in the input list is preserved. *)
quicksort [6;5;1;61;76;31;9;4;2];;


let small = []
let large = [1; 5; 4; 2; 6; 61; 76; 31; 9]
merge l1 l2 = l1 @ l2
int list = [1; 5; 4; 2; 6; 61; 76; 31; 9]
(* gdy trafimy na najmniejszy element listy jako pierwszy, funkcja przestaje dzielić i cały czas się wywołuje co da stack overflow *)
quicksort [6;5;1;61;76;31;9;4;2];;
mniejsza = [1;4;2;5]
wieksza = [61;76;31;9]
quicksort [1; 4; 2; 5; 6; 61; 76; 31; 9]


(*b*)
let rec quicksort' = function               
	[] -> 	[]        
| x::xs -> let small = List.filter (fun y -> y < x ) xs                     
					 and large = List.filter (fun y -> y >(*=*) x ) xs                     
					 in quicksort' small @ (x :: quicksort' large);; 
(* gdy trafimy kilka razy na tę samą wartość to jest on na liście końcowej tylko raz z powodu braku znaku >= przy large *)

quicksort' [6;5;1;61;76;31;9;4;2;6;6];;
															
(*ZAD 5*)

let rec insertsort = function
	| [] -> []
	| hd::tl -> let smaller = List.filter (fun y -> y< hd) tl
	            in insertsort (smaller @ (hd :: tl));;

insertsort [6;5;1;61;76;31;9;4;2;6;6];;
insertsort [1; 4; 2; 5; 6; 61; 76; 31; 9]


