package bobsdelights

abstract class Fruit (
    val name: String,
    val color: String
)

object Fruits {
    object Apple extends Fruit("apple", "red")
    object Orange extends Fruit("orange","orange")
    object Pear extends Fruit("pear", "yellowish")
    val menu = List(Apple, Orange, Pear)
}


//Can you Just in Time imports like
//def showFruit(fruit: Fruit) {
//  import fruit._
//  println(s"${name}s are $color")
//}
// val s = "Hello World" <- only for vals
// import s._
// charAt(6) <- This works
//

