/**
  * Created by jaideep on 03/06/17.
  */
package com.hello

import org.scalatest._

class HelloTest1 extends FunSuite {
  test("displaySalutation returns 'Hello World'") {
    assert(Hello.displaySalutation == "Hello World")
  }
}

class HelloTest2 extends FlatSpec {
  "displaySalutation" should "returns 'Hello World'" in {
    assert(Hello.displaySalutation == "Hello World")
  }
}

class HelloTest3 extends FunSpec {
  describe("When Display Salutation is called") {
    it ("returns Hello World") {
      assert(Hello.displaySalutation == "Hello World")
    }
  }
}

class HelloTest4 extends WordSpec {
  "Calling" when {
    "display salutation" should {
      "return Hello World" in {
        assert(Hello.displaySalutation == "Hello World")
      }
    }
  }
}

class HelloTest5 extends FreeSpec {
  "Calling" - {
    "display salutation" - {
      "return Hello World" - {
        assert(Hello.displaySalutation == "Hello World")
      }
    }
  }
}

class HelloTest6 extends Spec {
  object `Calling` {
    object `display salutation` {
      def `return Hello World` {
        assert(Hello.displaySalutation == "Hello World")
      }
    }
  }
}