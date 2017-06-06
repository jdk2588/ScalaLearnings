package com.packt.examples.beforeandafter

import org.scalatest.{BeforeAndAfter, FlatSpec}

import scala.collection.mutable.ListBuffer

/**
  * Created by jaideep on 05/06/17.
  * BeforeAndAfter Use this design when you need to perform the same side effects before and or after the tests,
  * rather than at the beginning or end of tests.
  *
  * BeforeAndAfterEach Use when you want to stack traits that perform the same side effects before and or after tests,
  * rather than at the beginning or end of tests.
  */

class ExampleSpec extends FlatSpec with BeforeAndAfter {
  val builder = new StringBuilder
  val buffer = new ListBuffer[String]
  before {
    builder.append("Scalatest is ")
  }

  after {
    builder.clear()
    buffer.clear()
  }

  "Testing" should "be easy" in {
    builder.append("easy!")

    assert(builder.toString === "Scalatest is easy!")
    assert(buffer.isEmpty)
    buffer += "sweet"
  }

  it should "be fun" in {
    builder.append("fun!")
    assert(builder.toString === "Scalatest is fun!")
    assert(buffer.isEmpty)
  }
}
