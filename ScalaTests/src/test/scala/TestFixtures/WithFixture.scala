/**
  * Created by jaideep on 05/06/17.
  */

/** The recommended default approach wjen most or all thest need the same fixture treatment.
  * This general technique allows you, for example to perform side effect at beginning and end of all
  * or most tests, transform the outcome of tests, retry tests, make decision based on test names,
  * tags, or other test data. Use this technique unless
  *           - Different tests need different fixtures(refactor using Scala instead)
  *           - An exception in fixture code should abort the suite, not fail the text(use a before and after trait instead).
  *           - You have objects to pass into tests(override with fixtures)
  *
  * withfixture(OneArgsTest) Use when you want to pass the same fixture object or objects as a parameter into all or most tests.
  */

package com.packt.example.withfixture

import java.io.File
import org.scalatest._

class WithFixtureTest extends FlatSpec {

  override def withFixture(test: NoArgTest) = {
    super.withFixture(test) match {
      case failed: Failed =>
        val currDir = new File(".")
        val fileNames = currDir.list()
        info("Dir snapshot: " + fileNames.mkString(", "))
        failed

      case other => other
    }
  }

  "This test" should "succeed" in {
    assert(1 + 1 === 2)
  }

  it should "fail" in {
    assert(1 + 1 === 3)
  }
}
