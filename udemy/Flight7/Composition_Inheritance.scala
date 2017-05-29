//Defining Paramterless Methods
object L6 {
    def returnInt: Int = 42
    //So if you have
}

abstract class Element {
    def contents: Array[String]
    def height: Int = contents.length
    def width: Int = if (height == 0) 0 else contents.map(_.size).max
    def example: Int
}

//Scala does have Primary Constructor that is called when class is called 
//by itself first. For others use Auxillary constructor

abstract class ArrayElement(consts: Array[String]) extends Element {
    val contents: Array[String] = consts
}

//Invoking superclass constructors

class LineElement(s: String) extends ArrayElement(Array(s)) {
    //If it is a val in super class then in the child class it has to be 
    //overriden by a val, but if it def in super class it can be overriden by
    //a val in child class

    //Uniform access principal allows you to change a parameterless def into
    //a val without breaking client code 

    //The promise is broken of val
    override def width = s.length
    override def height = 1

    //Use override to override a base class function
    //You have a choice to use override or not if the method in the base class
    //is abstract
    def example: Int = 1
}


//You can have final keyword in scala, a final member cannot be override in hierarchy 
//you cannot subclass or extend a final class

