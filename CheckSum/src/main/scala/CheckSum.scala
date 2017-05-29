/**
  * Created by jaideep on 22/05/17.
  */
class Sum {
  private var sum = 0
  def add(b: Byte): Unit = {sum += b}
  def checksum(): Int = ~(sum & 0xFF) + 1
}


import scala.collection.mutable
//Creates an object companion which acts as single instance of the class Sum
object Sum {
  private val cache = mutable.Map[String, Int]()

  def calculate(s: String): Int = {
    if (cache.contains(s)) {
      cache(s)
    } else {
      val acc = new Sum
      for (c <- s) acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s->cs)
      cs
    }
  }
}

//without extending App
/*object CheckSum {
  def main(args: Array[String]): Unit = {
    var value = ""
    if (args.length == 0) {
      value = "Hello World"
    } else {
      value = args(0)
    }
    println(value)
    println(Sum.calculate(value))
    println(Sum.calculate(value))
  }
}*/

//with extending App
object CheckSum extends App {
  var value = ""
  if (args.length == 0) {
    value = "Hello World"
  } else {
    value = args(0)
  }
  println(Sum.calculate(value))
}