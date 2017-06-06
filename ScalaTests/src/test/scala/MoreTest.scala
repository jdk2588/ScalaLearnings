/**
  * Created by jaideep on 03/06/17.
  */
import org.scalatest._

object database {
  def isAvailable() = true
}

import org.scalatest._
import Matchers._

class MoreTest extends FunSuite with Matchers {

  def someMethod = println("Hello")

  object War {
    def over = true
  }
//  def war = true
  test("one plus one") {

    val two = 2
    val three = 3
    assert(1 + 1 == two)
    assert(1 + 1 != three)

    assertResult(two){ 1 + 1 }

    //intercept[IllegalArgumentException] {
    //  someMethod
    //}

    try {
      someMethod
      fail("Should not be here")
    } catch {
      case _: IllegalArgumentException => //Expected so continue
      case _ => "Unexpected"
    }

    //Deliberaltely failing test
    //fail()
   // fail("Failure Message")

    assume(database.isAvailable())

    //cancel()

    //cancel("I cancelled it deliberaltely")

    assert("Hello".length == 5, "Message")
    assertResult(5, "Message"){"Hello".length}

    withClue("Message") {
      intercept[IllegalArgumentException] {
        someMethod
      }
    }

    val message = "Hello World"

    message should equal ("Hello World")

    message should be ("Hello World")
    message shouldBe "Hello World"

    message should === ("Hello to World")
    message shouldEqual "Hello World"

    message shouldBe a [String]
    message should not be an [Int]

    //message should be a [Seq[_]]

    //obj1 should be theSameInstances obj2

    message should have length 10
    message should have size 200

    message should startWith ("Hello")
    message should endWith ("rld")
    message should not include ("Batman")

    message should endWith ("wor.d")
    message should fullyMatch regex ("[A-Za-zs]+")

    "123zyx321" should startWith regex ("([d]+)" withGroups("123"))

    val number = 8
    number should be <= 7
    number should be >= 7
    number should be > 7

    War shouldBe 'over

    val voltage = 10

   // voltage should equal (12.0 +- 0.5)
   // voltage should be (12.0 +- 0.5)
    voltage shouldBe 240 +- 10


    None shouldBe empty

    "" shouldBe empty

    new java.util.HashMap[Int, Int] shouldBe empty

    new { def isEmpty = true} shouldBe empty

    Array(1,2,3) should not be empty

    (List("Hi","Di","Ho") should contain ("ho"))
    List(1,2,3,4,5) should contain oneOf (5,7,9)
    List(1,2,3,4,5) should contain noneOf (7,8,9)

    List(1,2,2,3,3,3) should contain inOrderOnly (1,2,3)
    List(0,1,2,2,99,3,3,3,5) should contain inOrder (1,2,3)
    List(1,2,3) should contain theSameElementsInOrderAs collection.mutable.TreeSet(3,2,1)

    List(1,2,3) shouldBe sorted

    val xs = List(1,2,3)

    all(xs) should be < 10

    //Inspectors
    all(xs) should be > 0
    atMost(2, xs) should be >= 4
    atLeast(3, xs) should be < 5
    between(2,3, xs) should be (be > 1 and be < 5)
    exactly(2,xs) should be <= 2
    every(xs) should be < 10

    //map should (contain key ('two') and not contain value (7))

    //option shouldEqual None
    //option shouldBe None
    //ption should === (None)

  }
}
