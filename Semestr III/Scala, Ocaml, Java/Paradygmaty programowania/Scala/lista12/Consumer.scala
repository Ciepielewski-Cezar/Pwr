class Consumer(name: String, buf: Buffer) extends Thread(name) {
  override def run() {
    var msg = ""
    do {
      msg = buf.take
    } while (msg != "Done")
  }
}

object prodCons {
  def main(args: Array[String]) {
    val buf = new Buffer
    new Producer("Producer", buf).start
    new Consumer("Consumer", buf).start
  }
}