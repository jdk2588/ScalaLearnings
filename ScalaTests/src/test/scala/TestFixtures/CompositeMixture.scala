package com.packt.examples.composingwithfixture

/**
  * Created by jaideep on 05/06/17.
  */

/*
* In larger projects, teams often end up with several different fixtures that test classes need in different combinations,
* and possibly initialized (and cleaned up) in different orders, A good way to accomplish this in ScalaTest is to factor
* the individual fixtures into traits that can be composed using stackable trait pattern. This can be done for example
* by placing withFixture.
* */

import org.scalatest._
import collection.mutable.ListBuffer

trait Builder extends FlatSpec with SuiteMixin {
  this: Suite =>
  val builder = new StringBuilder

  abstract override def withFixture(test: NoArgTest) = {
    builder.append("ScalaTest is ")
    try super.withFixture(test)
    finally builder.clear()
  }

}

trait Buffer extends FlatSpec with SuiteMixin {
  this: Suite =>
  val buffer = new ListBuffer[String]

  abstract override def withFixture(test: NoArgTest) = {
    try super.withFixture(test)
    finally buffer.clear()
  }
}

/*
* By mixing in both the Builder and Buffer traits, ExampleSuite gets both fixtures,
 * which will be initialized before each test and cleaned up after.
 * The order the traits are mixed together determines the order of execution.
 * In this case, Builder is "super" to Buffer. If you wanted
* */

class ExampleSpec extends FlatSpec with Builder with Buffer {
  "Testing" should "be easy" in {
    builder.append("easy!")
    assert(builder.toString === "ScalaTest is easy!")
    assert(buffer.isEmpty)
    buffer += "sweet"

  }

  it should "be fun" in {
    builder.append("fun!")
    assert(builder.toString == "ScalaTest is fun!")
    assert(buffer.isEmpty)
    buffer += "clear"
  }
}

