import org.scalatest.FunSuite
import scala.collection.mutable.Stack

class ExampleSuite extends FunSuite {
    test("pop is invoked on a non-empty stack") {
        val stack = new Stack[Int]
        stack.push(1)
    }
}
