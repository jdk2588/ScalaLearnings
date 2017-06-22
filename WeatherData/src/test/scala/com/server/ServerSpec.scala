package com.server

import java.io.IOException
import java.net.{InetSocketAddress, Socket, SocketTimeoutException}

import org.scalatest._
import org.scalamock.scalatest.MockFactory

/**
  * Created by jaideep on 13/06/17.
  */
class ServerSpec extends FlatSpec with Matchers with MockFactory {
  "Connecting to local host server" should "work for port 9999" in {
    var isAlive = false
    // Creates a socket address from a hostname and a port number
    val hostName = "localhost"
    val port = 9999
    val socketAddress = new InetSocketAddress(hostName, port)
    val socket = new Socket

    val timeout = 2000

    try {
      socket.connect(socketAddress, timeout)
      socket.close()
      isAlive = true
    } catch {
      case exception: SocketTimeoutException =>
        println("SocketTimeoutException " + hostName + ":" + port + ". " + exception.getMessage)
      case exception: IOException =>
        println("IOException - Unable to connect to " + hostName + ":" + port + ". " + exception.getMessage)
    }

    isAlive should equal (true)
  }

  "Mock a Test function" should "work as expected" in {
    val fakeconn = stub[FakeConnection]
    fakeconn.getConn _ when ("localhost", 9999) returns "Hello World"
    Method
  }
}
