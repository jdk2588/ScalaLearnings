//Can call empty paren methods with or without parens
    // def example(): Int = 42
    //example() <- this will work fine
    //example <= this will work fine

//Can only call parameterless methods without parens

    // def example: Int = 42
    //example() <- this will give error
    //example <= this will work fine

//Recommended: Use parens if side effects



abstract class Element {
    def contents: Array[String]
    def height: Int = contents.length
    def width: Int = if(height == 0)0 else contents.map(_.size).max
}
