
class Producer(name: String, buf: Buffer) extends Thread(name) {
  override def run(): Unit = {
    for (i <- 1 to 10) buf.put("m" + i)
    buf.put("Done")
  }
}