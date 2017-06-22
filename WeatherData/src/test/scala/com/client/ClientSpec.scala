package com.client

import org.scalatest._


/**
  * Created by jaideep on 12/06/17.
  */
class ClientSpec extends FlatSpec with Matchers {
  "Test to check generation of logs" should "return " in {
    val Sample = Client.generateMultipleLog
    Sample.length shouldEqual 100
  }

}
