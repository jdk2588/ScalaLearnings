package TestFixturesImplementation

import com.decimalbinary.{BaseConversion, Decimal, Hexadecimal, UnitSpec}

/**
  * Created by jaideep on 05/06/17.
  */
class DecimalHexadecimal extends UnitSpec {
  "base conversion utility" should "convert a number 1243 into a binary number 4DB" in {
    var hex: Hexadecimal = BaseConversion.decimalToHexaDecimal(Decimal("1243"))
    assert(hex.number == "4DB")
  }

  it should "convert a number 11111122 into a hexadecimal number A98AD2" in {
    var hex: Hexadecimal = BaseConversion.decimalToHexaDecimal(Decimal("11111122"))
    assert(hex.number == "A98AD2")
  }
}
