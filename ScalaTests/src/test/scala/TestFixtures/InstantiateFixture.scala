/*
* Recommended techniques for reusing the code in ScalaTest
*     - Refactor using Scala
*     - Override withFixture
*     - Mixin a before and after trait
*
*     get-Fixture methods - This method helps to create a fresh instance of mutable fixture objects in each test that needs them,
*     but does not help clean them up when you are done.
*
*     fixture-content objects: By placing fixture methods and fields into traits, you can easily give each test just the newly
*     created fixture it needs by mixing together traits. Use this technique when you need different combinations of mutable
*     fixture objects in different tests and dont need to cleanup after
*
*     loan-fixture methods : Factor out duplicate code with loan pattern when different tests need different fixtures that
*     must be cleaned up afterwards.
*
*
*     Trait suite implementation of runTest passes a no-arg test function to withFixture(NoArgTest)
* */

package com.packt.example.fixturecontext

import org.scalatest.FlatSpec
import collection.mutable.ListBuffer

class FixtureContext extends FlatSpec {

  trait Builder {
    val builder = new StringBuilder("Scalatest is ")
  }

  trait Buffer {
    val buffer = ListBuffer("Scalatest", "is")
  }

  "Testing" should "be productiove " in new Builder {
    builder.append("productive!")

    assert(builder.toString === "Scalatest is productive!")
  }

  "Test code" should "be readable" in new Buffer {

    buffer += ("readable!")

    assert(buffer === List("Scalatest", "is", "readable!"))
  }
  //This test need both the fixtures

  it should "be clear and concise" in new Builder with Buffer {
    builder.append("clear!")
    buffer += ("concise!")
    assert(builder.toString === "Scalatest is clear!")
    assert(buffer === List("Scalatest", "is", "concise!"))
  }
}

