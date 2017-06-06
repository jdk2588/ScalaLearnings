package TestFixturesImplementation

import com.decimalbinary.{BaseConversion, Decimal, Hexadecimal, UnitSpec}

/**
  * Created by jaideep on 05/06/17.
  */
class HexadecimalBinarySpec extends UnitSpec {
  "base conversion utility" should "convert a hexadecimal number AB to equivalent 171" in {
    var decimal: Decimal = BaseConversion.hexadecimalToDecimal(Hexadecimal("AB"))
    assert(decimal.number === "171")
  }

  it should "convert hexadecimal number 123AB to decimal equivalent 74667" in {
    var decimal: Decimal = BaseConversion.hexadecimalToDecimal(Hexadecimal("123AB"))
    assert(decimal.number === "74667")
  }

  it should "convert hexadecimal number ABCDEF to decimal equivalent 11259375" in {
    var decimal: Decimal = BaseConversion.hexadecimalToDecimal(Hexadecimal("ABCDEF"))
    assert(decimal.number === "11259375")
  }
}
