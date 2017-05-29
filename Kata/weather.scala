/*Both Array and ArrayBuffer are mutable, which means that you can modify elements at particular indexes: a(i) = e
ArrayBuffer is resizable, Array isn't. If you append an element to an ArrayBuffer, it gets larger. If you try to append an element to an Array, you get a new array. Therefore to use Arrays efficiently, you must know its size beforehand.Arrays are implemented on JVM level and are the only non-erased generic type. This means that they are the most efficient way to store sequences of objects – no extra memory overhead, and some operations are implemented as single JVM opcodes.ArrayBuffer is implemented by having an Array internally, and allocating a new one if needed. Appending is usually fast, unless it hits a limit and resizes the array – but it does it in such a way, that the overall effect is negligible, so don't worry. Prepending is implemented as moving all elements to the right and setting the new one as the 0th element and it's therefore slow. Appending n elements in a loop is efficient (O(n)), prepending them is not (O(n²)).Arrays are specialized for built-in value types (except Unit), so Array[Int] is going to be much more optimal than ArrayBuffer[Int] – the values won't have to be boxed, therefore using less memory and less indirection. Note that the specialization, as always, works only if the type is monomorphic – Array[T] will be always boxed.*/

object Weather {
    val file = scala.io.Source.fromFile(new java.io.File("weather.dat"))
    val lines = file.getLines.filter(l => l.length != 0).toList

    //foreach returns nothing(i.e Unit) whereas map returns a Type as expected
    var storage = lines.tail.reverse.tail.reverse.map(l => l.trim.split("\\s+"))

    val day = storage.scanLeft(0)((x,y) => y(0).toInt).tail
    val temp1 = storage.scanLeft(0)((x,y) => if (y(1).endsWith("*")) y(1).dropRight(1).toInt else y(1).toInt).tail
    val temp2 = storage.scanLeft(0)((x,y) => if (y(2).endsWith("*")) y(2).dropRight(1).toInt else y(2).toInt).tail

    val temp_diff = (day, temp1, temp2).zipped.map((a, b, c) => (a, b-c)).sortBy(_._2).head
}
