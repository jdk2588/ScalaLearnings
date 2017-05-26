import scala.io.Source

object LongLines  {
    def processFile(filename: String, width: Int): Unit = {
    /*    def processLine(line: String): Unit = {
            if (line.length > width)
                println(s"$filename: ${line.trim}")
        }*/

        //Anonymous function
        val processLineFun = (line: String) => {
            if (line.length > width)
            println(s"$filename: ${line.trim}")
        }

        val extension = ".scala"
        val source = Source.fromFile(filename + extension)
        for (line <- source.getLines)
            processLineFun(line)
    }
}
