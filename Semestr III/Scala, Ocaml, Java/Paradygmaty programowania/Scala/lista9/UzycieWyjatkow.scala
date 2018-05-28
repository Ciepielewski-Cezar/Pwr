

object UzycieWyjatkow {
  def main(args: Array[String]) {
    try {
      metoda1()
    } catch {
      case e: Exception => System.err.println(e.getMessage + "\n")
        e.printStackTrace()
    }
  }
  
  
  
  def metoda1() {
    metoda2()
  }
  def metoda2() {
    metoda3()
  }

  def metoda3() {
    throw new Exception("Wyjatek zgloszony w metoda3")
  }
}

/*
	java.lang.Exception: Wyjatek zgloszony w metoda3
  at .metoda3(<console>:19)
  at .metoda2(<console>:15)
  at .metoda1(<console>:12)
  ... 48 elided
 */

