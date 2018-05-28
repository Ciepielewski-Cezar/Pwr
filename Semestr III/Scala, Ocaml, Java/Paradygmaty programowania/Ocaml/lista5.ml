(*Zad 1*)

type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);;

let lrepeat llista k =
	let rec helper (result,iter) =
		match (result, iter) with
		| (LNil,_) -> LNil
		| (LCons(_,t), 0) -> helper(t(),k)
		|	(LCons(h,_), _) -> LCons(h, fun() -> helper(result,iter - 1))
	in helper(llista, k)
	;;

let lLista1 = (LCons(1, function () -> LCons(2, function () ->LCons(3, function () -> LCons(4, function () ->LCons(5, function () -> LNil))))));;
let x = lrepeat lLista1 3;;
ltake(15,x)
int list = [1; 1; 1; 2; 2; 2; 3; 3; 3; 4; 4; 4; 5; 5; 5]

(*lfrom*)
let rec lfrom k =
    LCons (k, function () -> lfrom (k+1))
    ;;

(*ltake*)
 let rec ltake = function
    | (0, _) -> [] 
    | (_, LNil) -> []
    | (n, LCons(x,xf)) -> x::ltake(n-1, xf())
    ;;

(*toLazyList*)
let rec toLazyList = function
    | [] -> LNil
    | h::t -> LCons(h, function () -> toLazyList t)
    ;;



(*Zad 2*)

let lfib =
	let rec fib a b = 
		LCons(a, fun() -> fib b (a + b))
	in fib 1 1
	;;



(*Zad 3*)

type 'a lBT = LEmpty | LNode of  'a * (unit ->'a lBT) * (unit -> 'a lBT);;



(*a)*)

let rec lTree n =
	LNode(n, (fun () -> lTree(2*n)), (fun () -> lTree(2*n+1)))
	;;



(*b)*)

let breadthlBT lBT =
	let rec recTree = function
		| [] -> LNil
		| LEmpty :: tl         -> recTree tl
		| LNode(v, l, r) :: tl -> LCons(v, fun () -> recTree (tl @ [l(); r()]))
	in recTree [lBT]


