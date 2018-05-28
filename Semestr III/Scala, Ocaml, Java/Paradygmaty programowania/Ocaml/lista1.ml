let rec flatten listaList =
 if listaList = [] then []
	else List.hd listaList @ flatten(List.tl listaList)
	;;

flatten [[5;6];[1;2;3]];;


let rec count (obiekt, lista) =
	if lista = [] then 0
	else (if List.hd lista = obiekt then 1 else 0 ) + count(obiekt, List.tl lista)
	;;
count('a', ['a'; 'i'; 'a']);;

let rec replicate(obiekt, ilosc) =
	if ilosc < 0 then failwith "zla licba"
	else if ilosc = 0 then []
	else obiekt :: replicate (obiekt, ilosc - 1)
	;;
replicate("la", 3);;


let rec sqrList lista = 
	if lista = [] then []
	else (List.hd lista * List.hd lista) :: sqrList(List.tl lista)
	;;
sqrList[1;2;3;-4];;

let palindrome lista =
	lista = List.rev lista
	;;
palindrome['a'; 'l'; 'a'];;

let rec listLength lista =
	if lista = [] then 0
	else listLength(List.tl lista) + 1
	;;





type t1 = C of int | D of int
let s = 3 and t = C 2 and u = D 3

let f x =
	match x with
	| C s -> s + 10
	| D u -> u + 10
	| t -> 0;;

f t
f u
f s





let rec cos l =
match l with
[] -> ([], [])
| h::t -> match cos t with
|  (l1, l2) -> (h::l2, l1);;

cos [6; 5; 4; 3; 2; 1];;



let f z y = y (y z)







let t = [[2;3];[];[5]];;

let f t = List.map(List.filter( (<) 2 )) t ;;
f t;





let double x = x + x;;
let f1 t = List.map ( double) t;;

f1 [2;3]






let f x y z = x y(z x);;





module type S =
sig
type t
val y : t
end;;

module M1 : S with type t = float =
struct
type t = float
let x = 1.0
let y = x +. 2.0
end;;



























