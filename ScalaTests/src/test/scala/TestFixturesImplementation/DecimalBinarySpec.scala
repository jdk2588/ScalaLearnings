/**
  * Created by jaideep on 03/06/17.
  */
package TestFixturesImplementation

import com.decimalbinary.{BaseConversion, Binary, Decimal, UnitSpec}

class DecimalBinarySpec extends UnitSpec {
  "base conversion utility" should "convert a number 99 into a binary number 1100011" in {
    var binary: Binary = BaseConversion.decimalToBinary(Decimal("99"))
    binary.number should equal ("1100011")
  }
  it should "convert a number 245 into a binary number 11110101" in {
    var binary: Binary = BaseConversion.decimalToBinary(Decimal("245"))
    binary.number should be ("11110101")
  }
  it should "convert a number 3141 into a binary number 110001000101" in {
    var binary: Binary = BaseConversion.decimalToBinary(Decimal("3141"))
    binary.number shouldEqual  "110001000101"
  }
}
