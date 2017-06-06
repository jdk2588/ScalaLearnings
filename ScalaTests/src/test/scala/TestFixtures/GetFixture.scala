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


package com.packt.example.getfixture

import org.scalatest.FlatSpec
import collection.mutable.ListBuffer

class GetFixture extends FlatSpec {
  def fixture = new {
    val builder = new StringBuilder("Scalatest is ")
    val buffer  = new ListBuffer[String]
  }
  "Testing" should "be easy" in {
    val f = fixture
    f.builder.append("easy!")

    assert(f.builder.toString === "Scalatest is easy!")
    assert(f.buffer.isEmpty)
    f.buffer += "sweet"
  }

  it should "be fun" in {
    val f = fixture
    f.builder.append("fun!")
    assert(f.builder.toString === "Scalatest is fun!")
    assert(f.buffer.isEmpty)
  }
}

