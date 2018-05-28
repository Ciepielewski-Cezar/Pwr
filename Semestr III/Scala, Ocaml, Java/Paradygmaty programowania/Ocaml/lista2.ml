(*ZAD 1 dla ocamla rekurencja wykona się 1 *)

let rec evenR n = if n=0 then true else oddR(n-1) 
and oddR n = if n=0 then false else evenR(n-1);;

evenR 3;;



(*ZAD 2 *)

let rec fibonacci n =
	match n with
	| 0 -> 0
	| 1 -> 1
	| _ -> fibonacci(n-1) + fibonacci (n-2)


let fibonacci n = fHelper(n,0,1);;

let rec fHelper (n, f1, f2) =
	match n with
	| 0 -> f1
	| 1 -> f2
	| _ -> fHelper(n-1, f2, f1 + f2);;

fibonacci 42;;



(*ZAD 3 *)

let root3 a =
	let rec root3Helper x =
		if (abs_float((x ** 3.) -. a) <= (1e-15  *. abs_float(a))) then x
		else root3Helper(x +. (a /. (x ** 2.) -. x) /. 3.)
		in root3Helper(if a <= 1. then a else a /. 3.);;
		
		root3 9.0
		
		
		
(*ZAD 4 *)

let lista1 = [-2; -1; 0; 1; 2];;

let [_; _; x; _; _] = lista;;


let lista2 = [(1, 2); (0, 1)];;

let [(_, _); (x, _)] = lista2;;

		

(*ZAD 5 *)

let rec initSegment (segment, lista) =
	match (segment, lista) with
	| ([], _) -> true
	| (_, []) -> false
	| (_, _) -> if (List.hd segment = List.hd lista) then initSegment (List.tl segment, List.tl lista) else false;;
	

initSegment([1; 2; 3; 6], [1; 2; 3; 4; 5])




(*ZAD 6 *)

let rec replaceNth (lista, indeks, znak) =
	 match (lista, indeks) with
	| ([],_) 		-> []
	| h :: t, 0 -> znak :: t
	| h :: t, _ -> h :: replaceNth (t, indeks - 1, znak)

replaceNth (['o'; 'i'; 'a'], 1, 's')
	






