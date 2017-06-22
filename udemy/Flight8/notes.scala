/*
Scala's top classes
Scala's common superclass is Any(Object in Java)
Methods on Any can be invoked on any object
    - toString
    - equals, ==, !=
    - hashCode, ## 
    - asInstanceOf[<Type>]
    - isInstanceOf[<Type>]
 */
object L24 {
    /*Unlike Java, the equal method compare the equality of two objects
    with ==. In Java == operator compares "reference quality", but in Scala
    == is method you use on each class to compare the equality of two instances
     */
    val x = 7
    val ax: Any = 7
    val as: Any = "hello"

    ax.toString
    as.toString

    ax.equals(as)
    ax == as

    ax != as

    //For defining hashCode use hashCode
    ax.hashCode

    //To get hashCode 
    ax.##

    //Casting
    ax.isInstanceOf[Int]
    as.isInstanceOf[String]

}


/*
AnyVal and AnyRef extends superclass Any,
they split hierarchy into two different parts
AnyVal are superclasses to Value classes (Primitives in Java world) - 
Things like double, int, float, long, char, short , byte, Unit
AnyRef is superclass to reference classes with additional methods:
    - eq, ne(identity)
    - synchronized wait, notify, notifyAll
*/

object L25 {
    val s: AnyRef = "hello"
    s.isInstanceOf[String]
    val s2: AnyRef = new String("hello")
    s == s2 //returns true
    s eq s2 //returns false,(check references )
    //All Java classes dealt in Scala extend AnyRef (thus AnyRef is alias to java.lang.Object)
    //Scala inherits from a trait from scala object 
}


/*Implicit Conversions */

object L27 {
    //Implicit conversion of int and turn to long
    //Implicit conversions are designed by language deisgners, but in Scala
    // it has more general features are implicit conversions
    // - scala.AnyVal -> (Byte -> Short -> Int -> Long -> Float -> Double) 
    def doIt(x: Long): Long = x * 2
    doIt(4L)
    val i: Int = 4
    doIt(i)

    //The max method does not exist in int, gets converted to richer version to 
    //Int, Gets converted which has max method on it
    42 max 43 
}

//Scala Primitives
//Scala does not differentiate between primitives and boxed classes
//Behind the scene it does use Java  box classes when nexxessary
object L28 {
    //Scala does not have primitives, it auto boxes behind the scenes.
    8.toString
    8 + 7
}

// == in Scala is natural equality, for reference use .equals
// You can still oveeride.equals and == will use that
//Use eq/ne on AnyRef derivatives for references
object L29 {

    val s = "hello" //true 
    s.equals("hello") // true
    s == new String("hello") //true
    s eq new String("hello") //Will check the refernce in memory
}

//Bottom Types - at the bottom of Scala hierarchy of Fan-In , the bottom types
//are subclasses of every thing in the system

object L30 {
    val s: String = null
    import java.util.Date
    val d: Date = null

    //Both of s and d are null though of different type,

    //there is a sub-type of big type Null 


    //Nada of type null = Null because of there is one instance 
    //Null is a sub-type of all reference types

    val nada: Null = null

    val s: String = nada

    //Any cannot be assigned null, as null is ineligible for implicit conversion
    //Int is Any not AnyRef, null is absence of Reference. null is subtype of
    //AnyReference type. If we do not have Reference of we cant put null
//    val i: Int = null

    //Nothing is bottom type of Any. It is Nothing under the hierarchy 
    //Nothing bottom type to all things, and it is very useful
    //Unlike null there is no instance of Nothing, only exists as type 

//    val th = throw new Exception("hi")

    //Nothing is used for return type for Exception
    def fail(msg: String): Nothing = throw new Exception(msg)

    //we can use String, it will work but it will be lying
    //def fail(msg: String): String = throw new Exception(msg)

    //Nothing is used where List is used which is of Empty type
    //List are homogenous,

    //1 :: Nil
    //"hi":: Nil

    val nadalist: List[Nothing] = Nil
    //Because Nothing comes in bottom of hierarchy
    //Anything that is added at front of nadalist list will raise the homegenous type to 
    // most specific common super type. Most specific common super type for Int and Nothing will be
    // Int. For String and Nothing most specific common super type is String


    //If using an array of different data types, try using the AnyRef
    val arr = new Date() :: "Hello" :: nadalist
    //^^this will return a very specific inferred type
//    val arr: List[AnyRef] = new Date() :: "Hello" :: nadalist

    // false :: 7 :: nadalist will return a list with datatype AnyVal
    // "s" :: false :: 7 :: nadalist
    //List[Any] = List(s, false, 7)

    val un: Unit = ()
    /*
     Unit means that (a) function has side effects like input and output, (b) these side effects are the main goal of the function. Of course, function can have side effects even if it's type is different from Unit.

     Nothing is a special type in Scala, because (a) it has no values (Unit has exectly one value - ()), so you cannot return value of type Nothing and (b) it is a subtype of every other types. That means, that if something has type Nothing, it can be used in every place where other type is required, but it won't produce any result. This is used for dealing with exceptions - throw expression has a type of Nothing, so it can be used basically everywhere in a program.

     Simply, Nothing means that there was an error and nothing was returned, while Unit means there were side effects.
     */

    //^^ Unit is equivalent to void in Java, but it helps scala type referencing 
    // system to work. If there was no return type in scala it would have been 
    // hard to infer the type, but Unit helps it to be consistent.


    //Now if you look at the function divide its return type specified is Int,
    //but by branch it will return either an Int or Nothing. It will look for the 
    //most common superclass in the hierarchy and since Nothing is underneath everything
    //else in hierarchy, so Int is the return type. Nothing is sub-type and does not change
    //overall return statement when it is used. Imp : Nothing can be used when trying to raise
    //exceptions

    def divide(x: Int, y: Int): Int =  
        if (y != 0) x/y else fail("div by zero")
}

object L32 {
    //Scala Negatives

    // -- Nil the empty list
    
    // Null - type of null(bottom type of AnyRefs), do not use Null, The creator 
    // of null concept in C++ wrote a paper million dollar mistake and has cost
    // of tons and tons of money.

    // Nothing - bottom type of Any

    // None - the "new null" Safer null, how to use null pointer exceptions
    //Option could be considered as box which can have it or it cannot, with at most one thing or nothing
    //It is safer way of handling optional values 

    val nums = Map("I" -> 1, "II" -> 2)
    //use
    nums("I")
    nums("II")
    //but what about 
    //nums("III") 
    //results in null pointer exception, so use
    nums.get("III") 
    //^^ results in Options[Int] = None

    nums.get("II")
    //^^ results in Option[Int] = Some(2) , but this cannot be used directly as
    //Int, so use something like nums.getOrElse(0) which will result in 0
    //by default

    /* to get an Int directly use like this sets up a default value*/
   nums.get("II").getOrElse(0)

}

//String interpolation
object L34 {
    val str = "the square of " + 2 + " is " + (2 * 2)

    val x = 2

    val stri = s"the square of $x is ${x*x}"
}


//Allowing to do implicit conversions, like wrap or extend a particluar class
//It is like boxing an item. Not that as overhead

/*Value Classes 
class MyValueClass[U](val underlying: U) extends AnyVal
1. Must have one (and only one) paramteric field
2. Cannot declare other fields
3. Cannot have "constructor code"
4. Cannot define equals and hashCode
5. Either top level class or in a singleton object
 */
implicit class Fahrenheit(val temperature: Double) extends AnyVal {
    def isHot: Boolean = temperature > 80.0
    override def toString: String = s"Fahrenheit ($temperature)"
}
/* (87.0).isHot*/


//Equality Receipe
class RationalNum(n: Int, d: Int) {
    require(d != 0)

    private val g = gcd(n.abs, d.abs)
    val numer = (if (d < 0) -n else n) / g
    val denom = d.abs / g

    private def gcd(a: Int, b: Int): Int =
        if (b == 0) a else gcd(b, a % b)

    //Override with type Any only otherwise will be a compile error.
    override def equals(other: Any): Boolean = 
        other match {
        
            case that: RationalNum => (that canEqual this) && numer == that.numer  &&
            denom == that.denom

            case _ => false
        }


    // Why to have canEqual where an object cannot be compared with the underneath Any type
    //because it will not hold true vice-cersa. So, if A -> EmployeeName (String) and compare
    // Employename to String should hold true for String to EmployeeName, which is not reflexively true,
    //Use canEqual to have symmetry if A eq B, thus B eq A

    //Likewise collections cannot be compared like List, Vectors. 
    //It is a method which check if the other is an instance of same type that we want to check.
    def canEqual(other: Any): Boolean = other.isInstanceOf[RationalNum]


    //Supply consitent hashcode 
    override def hashCode: Int = 
        41 * ( 41 + numer ) + denom
}
