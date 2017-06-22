package com.server

import java.io._
import java.net.{ServerSocket, Socket, SocketException}
import java.util.Random

//
///**
//  * Created by jaideep on 12/06/17.
//  */
//
//import java.net._
//import java.io._
//import scala.io._
//
//object Server {
//
//  def receive = {
//    val server = new ServerSocket(9999)
//    while (true) {
//      println("Receiveing....")
//      val s = server.accept()
//      val in = new BufferedSource(s.getInputStream())
//      val out = new PrintStream(s.getOutputStream())
//
//      out.println(in.next())
//      out.flush()
//      s.close()
//    }
//  }
//}
//
//object ServerObj extends App {
//  Server.receive
//}
//
//object Client {
//
//  def send = {
//    println("Sending....")
//    val s = new Socket(InetAddress.getByName("localhost"), 9999)
//    lazy val in = new BufferedSource(s.getInputStream()).getLines()
//    val out = new PrintStream(s.getOutputStream())
//
//    out.println("Hello, world")
//    out.flush()
//    while (true) {
//      println("Received: " + in.foreach())
//    }
//
//    s.close()
//  }
//}
//
//object Simulate extends App {
//  Client.send
//}

trait FakeConnection {
  def getConn(host: String, port: Int): String
}

object Server extends App {

  try {
    val listener = new ServerSocket(9999);
    while (true)
      new Server(listener.accept()).start()
    listener.close()
  }
  catch {
    case e: IOException =>
      System.err.println("Could not listen on port: 9999.");
      System.exit(-1)
  }
}

case class Server(socket: Socket) extends Thread("ServerThread") {

  override def run(): Unit = {
    val rand = new Random(System.currentTimeMillis())
    try {
      val in = new DataInputStream(socket.getInputStream())
      val out = new FileOutputStream("/tmp/tempfile")

      in.readChar()
      while (true) {
        var data = in.read()
        out.write(data.toChar)
        while (data != -1)  {
          data = in.read()
          out.write(data.toChar)
        }
        out.flush()
        Thread.sleep(1000)
      }
      out.close()
      in.close()
      socket.close()
    } catch {
      case e: SocketException =>
        () // avoid stack trace when stopping a client with Ctrl-C
      case e: IOException =>
        e.printStackTrace();
    }
  }
}