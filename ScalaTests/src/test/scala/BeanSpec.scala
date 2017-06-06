/**
  * Created by jaideep on 03/06/17.
  */
package com.decimalbinary

import org.scalatest.FlatSpec

class BeanSpec extends FlatSpec {
  "Decimal" should "throw error when initalized with a non numeric string" in {
    try {
      Decimal("XYZ")
    }
    catch {
      case e: IllegalArgumentException => assert(e.getMessage == "requirement failed: Unable to convert string to number")
      case _ => fail
    }
  }


  "Binary" should "throw error when initalized with a non numeric string" in {
      intercept[NumberFormatException] {
        Binary("XYZ")
      }
  }

  "HexaDecimal" should "throw error when initalized with a non numeric string" in {
    intercept[IllegalArgumentException] {
      Hexadecimal("ABCD")
    }
  }

}
