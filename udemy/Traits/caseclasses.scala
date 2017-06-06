/*Case classes are like regular classes with a few key differences:-
    
    - Good for modelling immutable data
*/

/*
 See here new was not used to instatiate a Book class because they have
 apply method by default which does object construction by default

 The parameters are public vals, cant reassingn the name param, though vars 
 can exist but discouraged.

 Case classes are compared by structure not by reference, thus book1 and book2
 will be same book1 == book4 => true

 Can create deep copy and also change the arguments partially and rest will be taken 
 by default.
*/
case class Book(name: String, price: Int = 200)
val book1 = Book("Functional Programming in Scala")
val book4 = Book("Functional Programming in Scala")
val book2 = Book("Play framework")
val book3 = book1.copy(name="TDD in Scala")
