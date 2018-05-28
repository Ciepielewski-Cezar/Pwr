

class Pojazd(val producent: String, val model: String, val rokProdukcji: Int = -1, var rejestracja: String = "") {
  
  def this(producent: String, model: String, rejestracja: String) {
    this(producent, model, -1, rejestracja)
  }

  
  override def toString = "[" + producent + ", " + model + ", " + rokProdukcji + ", " + rejestracja + "]"
}

val jeden = new Pojazd ("Audi", "A6")
jeden.toString()

val dwa = new Pojazd ("Audi", "A6", 2010)
dwa.toString()

val trzy = new Pojazd ("Audi", "A6", 2010, "WR9010")
trzy.toString()

val cztery = new Pojazd ("Audi", "A6", "WR9010")
cztery.toString()

