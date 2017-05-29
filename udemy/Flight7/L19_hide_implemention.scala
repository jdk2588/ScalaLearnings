import Element.elem

abstract class Element {
    val contents: Array[String]
    val height: Int = contents.length
    val width: Int = if (height == 0) 0 else contents.map(_.size).max

    def above(that: Element): Element = 
    //This wont work because Class cannot access object members directly
    //though object members can access class, in order to access object 
    //members import the object like done at top 
        elem(this.contents ++ that.contents)

    def beside(that: Element): Element = 
        elem(
            for (
                (line1, line2) <- this.contents zip that.contents
            ) yield line1 + line2
        )

    override def toString = contents mkString "\n" 
}


//Factory methods in comapnion object
object Element {
    //These classes can be hidden and the scope can be limited 
    class ArrayElement1(val contents: Array[String]) extends Element

    class LineElement(s: String) extends ArrayElement1(Array(s)) {
        override val width = s.length
        override val height = 1

        def example: Int = 1
    }

    class UniformElement(
        ch: Char,
        override val width: Int,
        override val height: Int
    ) extends Element {
        private val line = ch.toString * width
        val contents = Array.fill(height)(line)
    }
    def elem(contents: Array[String]): Element =
        new ArrayElement1(contents)

    def elem(char: Char, width: Int, height: Int): Element =
        new UniformElement(char, width, height)

    def elem(line: String): Element =
        new LineElement(line)
}
