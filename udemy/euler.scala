//Before starting 

object Views {
    //Creates a lazy SeqView
    var r = (1 until 1000).view
    var k = r.foreach(x => x*2)
}

//Scala Stream is like a List except that it elemeents are computed lazily
//how a view creates a lazy version of a collection. A Stream can be long 
//infinitely long. Like a view elements that are accessed computed,
//a Stream behaves similar to a List
//As a List can be constructed with :: a Stream can be constructed with 
//#:: Use Stream.empty at the end of expression instead of Nil 

object TestStream {
//    val jstrm = 1 #:: 2 #:: 3 #:: Stream.empty
    // or can be used something like
    var jstrm = (1 to 100000000).toStream
    jstrm.take(3)
    jstrm.filter(_ < 200)
    jstrm.filter(_ > 200)
    jstrm.map { _*2}
    //You will see Stream created with (1,?). This question mark 
    //suggests that tail of the stream has not been evaluated
    // stream.head will give output, whereas stream.tail will 
    //show Stream(2,?)

    //Do not use functions like :- 
    //jstrm.max
    //jstrm.size
    //jstrm.sum
    //Why ? Because they are not transformer methods and evaluated immediately and can cause out of memory error

    //Transformer methods are collection methods that convert a given input 
    //collection to a new output collection, methods like map,filter and reverse
}

object P1 {
    var n = (1 until 1000).filter(x => x%3 == 0 || x%5 == 0).sum
}


//reduceLeft, foldLeft, reduceRight and foldRIght
//These functions are used to walk through the elements in a sequence,
//applying function to neighbouring elements to yield a new result.
//which is then compared to the next elements to yield a new result

//use reduceLeft to walk through a sequence from left to right, which starts 
//from left and compares next element and yields a new result. The result is then 
//compared to third element and so on till the end of list

object ScalaReduceScan {
    var a1 = Array(10,8,5,4,2,6)
    
    a.reduceLeft(_ + _)
    // could be written as
    a.reduceLeft((x,y) => x + y)
    //how it works (((((10+8) + 5) + 4) + 2) + 6) 

    a.reduceRight(_ + _)
    //this will work as 
    //(10 + (8 + (5 + (4 + (2 + 6)))))

    a.scanRight(10)(_ * _)
    //^^ This will give like Array(192000, 19200, 2400, 480, 120, 60, 10)

    a.scanLeft(3)(_ * _)
    //^^ This will give like Array(3, 30, 240, 1200, 4800, 9600, 57600)

    //scanRight and scanLeft work in the same fashion as reduceRight and 
    //reduceLeft just that they take one argument which acts on the collection
    //and returns a sequence instead of value

}


//Find the sum of all even termed fibonnaci series upto 4 million
object P2 {
    val stream: Stream[Int] = 0 #:: stream.scanLeft(1)(_ + _)
    val r = stream.view.takeWhile(_ <= 4000000).filter(_ % 2 == 0).sum
}

//Get the largest prime factor of a composite number
//find returns the first element of a collection that matches a predicate function
object P3 {
    def factors(n: Long): List[Long] = (2 to math.sqrt(n).toInt).find(
    n%_==0).fold(List(n))(i => i.toLong :: factors(n/i))
}

//Find the largest palindrom 
//flatMap is a frequently used combinator that combines mapping and flattening
//flatMap takes a function that works on nested lists and concatenates the results
object P4 {
    val r = (100 to 999).view
            .flatMap(i => (i to 999).map(_*i))
            .filter(n=>n.toString == n.toString.reverse)
            .max
}

//Ranges are often used to populate data strctures and iterate over for loops
object P5 {
    val r = Range(20, Int.MaxValue).find(n=>Range(2,21).forall(n%_ == 0)).get
}
