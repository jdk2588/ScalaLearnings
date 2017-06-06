
package com.packt.example.withfixtureonearg

import org.scalatest.fixture
import java.io._
/**
  * Created by jaideep on 05/06/17.
  */
class ExampleSpec extends fixture.FlatSpec {
  case class FixtureParam(file: File, writer: FileWriter)

  def withFixture(test: OneArgTest) = {
    val file = File.createTempFile("hello", "world")
    val writer = new FileWriter(file)
    val theFixture = FixtureParam(file, writer)

    try {
      writer.write("ScalaTest is ")
      //set up the fixture
      withFixture(test.toNoArgTest(theFixture))
      //loan the fixture to the test
    } finally writer.close()
    // clean up the fixture
  }

  "Testing" should "be easy" in {
    f => f.writer.write("easy!")
      f.writer.flush()
      assert(f.file.length === 18)
  }

  it should "be fun" in {
    f => f.writer.write("fun!")
      f.writer.flush()
      assert(f.file.length === 17)
  }
}
