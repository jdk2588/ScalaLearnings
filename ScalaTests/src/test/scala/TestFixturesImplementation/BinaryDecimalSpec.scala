/**
  * Created by jaideep on 03/06/17.
  */
package TestFixturesImplementation

import com.decimalbinary.{BaseConversion, Binary, Decimal, UnitSpec}

class BinaryDecimalSpec extends UnitSpec {
  "base conversion utility" should "convert a decimal number 1100011 into a binary number 99" in {
    var decimal: Decimal = BaseConversion.binaryToDecimal(Binary("1100011"))
    decimal.number shouldEqual "99"
  }
  it should "convert a number 11110101 into a binary number 245" in {
    var decimal: Decimal = BaseConversion.binaryToDecimal(Binary("11110101"))
    decimal.number shouldBe "245"
  }
  it should "convert a number 110001000101 into a binary number 3141" in {
    var decimal: Decimal = BaseConversion.binaryToDecimal(Binary("110001000101"))
   // assert(decimal.number == "3141")
    decimal.number should equal ("3141")
  }

  it should "convert a number 100000000000001110000001 into a binary number 8389505" in {
    var decimal: Decimal = BaseConversion.binaryToDecimal(Binary("100000000000001110000001"))
    //assert(decimal.number == "8389505")
    decimal.number shouldEqual "8389505"
  }
}
