package com.packt.examples.composingbeforeandaftereach
/**
  * Created by jaideep on 05/06/17.
  */

import com.packt.examples.composingwithfixture.{Buffer, Builder}
import org.scalatest._

import collection.mutable.ListBuffer

trait Builder extends BeforeAndAfterEach {

  this: Suite =>
  val builder = new StringBuilder

  override def beforeEach() = {
    builder.append("ScalaTest is ")
    super.beforeEach()
    // To be stackable, must call super.beforeEach
  }

  override def afterEach() = {
    try super.afterEach()
    // To be stackable, must call super.afterEach
    finally builder.clear()
  }

}

trait Buffer extends BeforeAndAfterEach {

  this: Suite =>
  val buffer = new ListBuffer[String]

  override def afterEach() = {
    try super.afterEach()
    // To be stackable, must call super.afterEach
    finally buffer.clear()
  }

}

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

/*To get same ordering as withFixture
* super.beforeEach call at the end of each beforeEach method,
* and the super.afterEach call at the beginning of each afterEach method, as shown
* in the previous example. It is a good idea to invoke super.afterEach
* in a try block and perform cleanup in a final clause, as shown in the previous example,
* because this ensures the cleanup code is performed even if super.afterEach throws an exception.
* */

/*
The difference between stacking traits that extend BeforeAndAfterEach versus traits that implement withFixture is that setup
 and cleanup code happens before and after the test in BeforeAndAfterEach, but at the beginning and end of the test in withFixture.
 Thus, if a withFixture method completes abruptly with an exception, it is considered a failed test.
 By contrast, if any of the beforeEach or afterEach methods of BeforeAndAfterEach complete abruptly,
it is considered an aborted suite, which will result in a SuiteAborted event.
* */

