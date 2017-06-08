/**
  * Created by jaideep on 07/06/17.
  */

import org.scalatest._

import com.freqcalc.FreqCalc

import scala.io.Source

abstract class UnitSpec extends FlatSpec with Matchers

class TestSpec extends UnitSpec {
  val file = Source.fromResource("data.txt")

  "Reading from file" should "give SampleData" in {

    val source = file.getLines.toList

    source.map((l: String) => FreqCalc.freqMap(l))

  }

}
