/**
  * Created by jaideep on 22/05/17.
  */
//Functional way of doing it
object FizzBuzz {
  def main(args: Array[String]): Unit = {
    (1 until args(0).toInt).map(i => (i % 3, i % 5) match {
      case (0, 0) => "FizzBuzz"
      case (0, _) => "Fizz"
      case (_, 0) => "Buzz"
      case _ => i
    }).foreach(println)
  }
}

/*class FizzBuzz {
    private def isMultipleof3(num: Int): Boolean = {
      if (num % 3 == 0) true else false
    }

    private def isMultipleof5(num: Int): Boolean = {
      if (num % 5 == 0) true else false
    }

    def printFB(n: Int): Unit = {
        for (i <- 1 to n) {
          if (isMultipleof3(i) && isMultipleof5(i)) {
            println("FizzBuzz")
          } else if (isMultipleof5(i)) {
            println("Buzz")
          } else if (isMultipleof3(i)) {
            println("Fizz")
          } else println(i)
        }
    }
}

object Main {
  def main(args: Array[String]): Unit = {
    var fb = new FizzBuzz()
    println(args(0))
    fb.printFB(args(0).toInt)
  }
}*/