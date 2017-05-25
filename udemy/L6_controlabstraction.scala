//Creating own control abstractions, curry functions
import java.io._

object L124 {
    //Check if a list of numbers contain negative numbers

    //Imperative way
    def checkNeg(nums: List[Int]): Boolean = {
        var exists = false
        for (num <- nums)
            if (num < 0)
                exists =true
        exists
    }

    //What if if have to check number having larger than 100
    //Then need to change the func, which is not good
    //We need a way to write it more functional

    def checkLarge(nums: List[Int]) = nums.exists(_ > 100)
}


//has L126
object L125 {

    //This code is fine but only imperative way
    def printCurrentDateToFile(file: File): Unit = {
        val writer = new PrintWriter(file)
        try {
            writer.println(new java.util.Date)
        } finally {
            writer.close()
        }
    }

    //If we want to reuse the code, create a HOF, a lone pattern in scala
    // call something like L125.withPrintWriter(f, write => write.println("to be not to be"))

    def withPrintWriter(file: File, fn: PrintWriter => Unit): Unit = {
        val writer = new PrintWriter(file)
        try {
            fn(writer)
        } finally {
            writer.close()
        }
    }

}

//Currying : Term derived from Haskell where their is a function for exactly one
//thing and exactly one thing
object L127 {
    def sum(a: Int, b: Int): Int = a + b
    def curriedsum(a: Int)(b: Int): Int = a + b
}

//What happens with currying ?
//
object L128 {
    //Here you see first does not return value, but returns another function
    //Building as sequence of function done by currying
  def first(x: Int) = {(y: Int) => x + y} 
}

//Using more currying
object L129 {
    
   // Curly brackets and paranthesis are interchangeable so can use curly brace 
   //which looks like code block
   //withPrintWriter(file) { writer => 
   // | writer.println("Hello")
   // | writer.println("World")
   //}

    def withPrintWriter(file: File, fn: PrintWriter => Unit): Unit = {
        val writer = new PrintWriter(file)
        try {
            fn(writer)
        } finally {
            writer.close()
        }
    }
    def withPrintWriterCurried(file: File)(fn: PrintWriter => Unit): Unit = {
        val writer = new PrintWriter(file)
        try {
            fn(writer)
        } finally {
            writer.close()
        }
    }
}

//By -name paramters
object L130 {
    //What about functions which produce results but don't take any paramters
    //themselves

    var assertionsEnabled = true
    def myAssert(predicate: () => Boolean) = 
        if (assertionsEnabled && !predicate())
            throw new AssertionError


    //call like myAssert(() => 1 > 2)

    // It is hard to remember to fill empty parantehsis
    // so just use something without paranthesis and 
    // now can evaulate without () so using "paranthesis"
    // will just work and using myAssertWithout(2 > 3) will just work

    def myAssertWithout(predicate: => Boolean) = 
        if (assertionsEnabled && !predicate)
            throw new AssertionError
}


import scala.annotation.tailrec
//Frooty loops
object L131 {

    //If you look at the function, which means
    //if cond is satisfied, then run the body and call fruit recursively,
    //now when fruit is called, cond is not evaluated but passed as argument
    //to function


    //This fruit function could be written as 
    // L131.loop1(x < 10, {
    //| println(x)
    //| x += 1
    //| })

    @tailrec
    def loop1(cond: => Boolean, temp: Int, body: => Unit): Unit = 
        if (cond) {
            body
            loop1(cond, temp, body)
        }

    //This loop function could be written due to currying 
    // L131.loop2(x < 10) {
    //| println(x)
    //| x += 1
    //| })

    @tailrec
    def loop2(cond: => Boolean, temp: Int)(body: => Unit): Unit = 
        if (cond) {
            body
            loop2(cond, temp)(body)
        }
}
