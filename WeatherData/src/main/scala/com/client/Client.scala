package com.client

import java.io._
import java.net.{InetAddress, Socket}
import java.text.SimpleDateFormat
import java.util.{Calendar, TimeZone}

import scala.util.Random
/**
  * Created by jaideep on 12/06/17.
  */

class Log {

  val r = Random

  private val observatory = Vector("AU","US","FR","All Others")

  private def genObservatory: String = {
    observatory(r.nextInt(observatory.length))
  }

  private def genLocation: String = {
    s"${r.nextInt(1000)},${r.nextInt(1000)}"
  }

  private def genTemperature: String = {
    s"${r.nextInt(500)-250}"
  }

  private def genTimeStamp: String = {
    val tzgmt = TimeZone.getTimeZone("GMT")
    val timeinstance = Calendar.getInstance(tzgmt)
    timeinstance.set(2005 + r.nextInt(10),1 + r.nextInt(12),1 + r.nextInt(30),1 + r.nextInt(12),r.nextInt(60))
    new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").format(timeinstance.getTime)
  }

  private def generate: String = {
    s"$genTimeStamp|$genLocation|$genTemperature|$genObservatory"
  }
}

object Log {
  def generateLog: String = {
    val l = new Log()
    l.generate
  }
}

object Client {
  def generateOneLog: String = {
    Log.generateLog
  }

  def generateMultipleLog: Vector[String] = {
    var batchLogs = Vector[String]()
    (1 to 100).foreach((x) => { batchLogs = batchLogs :+ generateOneLog} )
    batchLogs
  }

  def sendToStream = {
    val streamwriter = new FileOutputStream("/tmp/tempfile")
    val stringreader = new StringReader(generateMultipleLog.toString)
    var data = stringreader.read()
    streamwriter.write(data)
    streamwriter.flush()

    while (data != -1) {
      data = stringreader.read()
      streamwriter.write(data)
    }

    streamwriter.close()
    stringreader.close()
  }


  def sendToSocket = {
    val ia = InetAddress.getByName("localhost")
    val socket = new Socket(ia, 9999)

    val out = new DataOutputStream(socket.getOutputStream)
    out.writeBytes(generateMultipleLog.mkString("\n"))
    out.flush()
    out.close()
  }
}

