import akka.actor._


/* W pierwszym przybliżeniu aktor jest wątkiem ze skrzynką pocztową. 
 * aktor jest reaktywnym obiektem. W odpowiedzi na otrzymywane komunikaty aktor może 
 * podejmować lokalne decyzje, tworzyć nowych aktorów, wysyłać nowe komunikaty i 
 * decydować, jak odpowiadać na kolejne komunikaty. 
 */


//definiujemy komunikaty jako klasę przypadku których instancje są domyslnie niemodyfikowalne
//i pozwalają na wykorzystywanie w mechaniie dopasowania do wzorca w metodzie receive
case class Ball(message: String, counter: Int)

class Player extends Actor {
  //musimy zaimplementować metodę receive która definiuje sposób reakcji aktora na przychodzące komunikaty
  //funkcja częściowa. Jest wykorzystywana przez aktorów przy obsłudze komunikatów. Zwykle definiowane jako ciąg przypadków
  def receive: PartialFunction[Any, Unit] = {  
    case Ball(_, 0) =>
      println("enough")
      context.system.terminate()  //kończymy działanie całego systemu akka
    case Ball("ping", k) =>
      println("pong")
      sender ! Ball("pong", k - 1)  //"!" wyślij komunikat asynchronicznie i kontynuuj działanie
    case Ball("pong", k) =>
      println("ping")
      sender ! Ball("ping", k - 1)  //sender(): ActorRef zwracającą referencję do nadawcy przetwarzanego komunikatu
    case _ =>
      println("error")
    
  }
}

object Main {  //obiekt singletonowy posiadający metodę main: Array[String] => Unit.
  def main(args: Array[String]) {
    val ourSystem = ActorSystem("MySystem")  //aktor który nadzoruje cały system aktorów danej aplikacji
    val playerOne = ourSystem.actorOf(Props[Player])  //za pomocą metody actorOf tworzymy egzemplarz aktora
    val playerTwo = ourSystem.actorOf(Props[Player])  //Props to niemodyfikowalny obiekt konfiguracyjny używany do tworzenia aktorów
    
    playerOne.tell(Ball("pong", 4), playerTwo)  // tell wysyłamy komunikat
  }
}


class Klasa[A, B] (var fst: A, var snd: B ){
  override def toString = "(" + fst + "," + snd + ")"
}

 Klasa x = (5,"abc")
 var a = "c"
 
 val x = new Klasa(5, "abc")