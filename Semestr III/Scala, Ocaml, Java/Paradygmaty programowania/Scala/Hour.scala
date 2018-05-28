package Cwiczenia

class Hour {
  private[this] var h: Int = _
  
  def hour: Int = h
  
  def hour_= (x: Int){
    require (0 <= x && x < 24)
    h = x
  }
}


object Hello {
  def main(args: Array[String]) {
    if (args.length == 0) println("Hello, welcome to Scala!")
    else for (cos <-args) println("Hello " + c+ ", welcome to Scala!")
    }
}

