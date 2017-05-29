object SomeCode {

    val incr = (x: Int) => x + 1
    def func() = {
        println(incr(2))
    }
}

object L106 {
    val someNums = List(-9, -7, -5, -3, -1, 0 ,1,2,3,4,5,6,7,8,9)
    someNums.foreach((x: Int) => println(x*x))
}

object L107 {
    
    val someNums = List(-9, -7, -5, -3, -1, 0 ,1,2,3,4,5,6,7,8,9)
    val one = someNums.filter((x: Int) => x > 0)
    
    //Can omit the x as we know that someNums is of type Int
    val other = someNums.filter(x => x < 0)
}

//Placeholder Syntax
object L108 {
    val someNums = List(-9, -7, -5, -3, -1, 0 ,1,2,3,4,5,6,7,8,9)
    
    //Can put an underscore since it has not to be used again. 
    val one = someNums.filter(_ < 0)
    val two = someNums.filter((_: Int) < 0)

    //val addem = (x: Int, y: Int) => x + y
    //or 
    val addem = (_: Int) + (_: Int)
}

//Converting a method to function value
object L109 {
   
    //You dont get object for processfile but for an anonymous function you do 
    //get an instance which could be run something like  val one = someNums.filter(_ < 0)
    //one.apply. Methods and function literals are not same thing, though they
    //are meant to perform the same action, functions are HOF(Higher Order Function) 

    def Sum(a: Int, b: Int, c: Int) = a + b + c
    val addem = (a: Int, b: Int, c: Int) => L109.Sum(a, b,c) 

}

//Using underscore to represent parameters
object L110 {

    //Transferring a named function of some kind to anonymous function
    def Sum(a: Int, b: Int, c: Int) = a + b + c
    val addem = L110.Sum(_, _, _) 
}

//Partially applied function
object L111 {

    //Can use like L111.addem(2)
    def Sum(a: Int, b: Int, c: Int) = a + b + c
    val addem = L111.Sum(1, (_: Int), 3)
    val addem1 = (b: Int) => L111.Sum(1, b, 3)
}

//If typing underscore is too much then
object L112 {
    //The Sum function if does not pass any parameters, will give a compiler 
    //error, can use like 'Sum _' to treat as partially applied function, which
    //can be used by assigning to a variable
    //it will tell compiler to treat it as fully or partially applied method

    val someNumbers = List(-11, -10, -5, 0, 5, 10)
    someNumbers.foreach(x=>println(x))
    someNumbers.foreach(println _)
    someNumbers.foreach(println)
    someNumbers foreach println

}

//Free variables and closures
object L113 {
    var more = 1
    //addMore is a closure because it is function literal which uses something
    //that is not passed to it, in this case more which is a reassignable value
    val addMore = (x: Int) => x + more
}

//Visibility Changes
object L114 {
    //This more value can be changed as it is var, and can be dangerous specially in 
    //Multithreaded enviornment

    var more = 100
    val addMore = (x: Int) => x + more
}

//Closure Parameters and Local variables 
object L115 {
    //IncreaseBy generates a function which can be used to increase value
    def IncreaseBy(howMuchMore: Int) = { (x: Int) => x + howMuchMore }
}

//Repeated arguments
object L116 {
    def echo(arg: String) = {println(arg)}
    def multiecho(args: Array[String]) = { args.foreach(println) } 
    def multiechowithoutarray(args: String*) = { args.foreach(println) } 
}

//Argument expansion
object L117 {

    //One can do pass like echo(Array("Hello"): _*), Results in wrapped array
    def echo(arg: String*) = {println(arg)}
}

//Named arguments
object L118 {
    // L118.details(name="Jaideep", age=28, sex='M')
    // This is invalide L118.details(age=28, name="Jaideep",'M') as positional after, named paramter
    def details(name: String, age: Int, sex: Char) = println(s"${name}, ${age}, ${sex}")
}

//Default value for parameters
object L119 {
    //Method overloading is not possible if default parameters are put
    def printTime(out: java.io.PrintStream = Console.out) {
        out.println(s"time = ${System.currentTimeMillis()}")
    }

    def printTime2(out: java.io.PrintStream = Console.out, divisor: Int=1) {
        out.println(s"time = ${System.currentTimeMillis()/divisor}")
    }
}

import scala.annotation.tailrec

//Tail Recursion, Better way is to get rid of Imperative style like loops(for, while)
//Idomatic style in Scala is functional, need to resort to 
//recursion, recusrsion has problem of being slower or run out of stack space
//encountering stackoverflow. If method is tailRecursive then Scala will optimize it
object L120 {

    //Imperative style
    def sumem(xs: List[Int]): Int = {
        var sum = 0
        for (x <- xs)
            sum += x
        sum
    }

    //Functional without tailrecursive 
    def sumemfunc(xs: List[Int]): Int = {
        if (xs.isEmpty) 0
        else xs.head + sumemfunc(xs.tail)
    }

    //Functional with tailrecursive 
    @tailrec
    def sumemfunc2(xs: List[Int], acc: Int=0): Int = {
        if (xs.isEmpty) acc
        else sumemfunc2(xs.tail, acc+xs.head)
    }


}
