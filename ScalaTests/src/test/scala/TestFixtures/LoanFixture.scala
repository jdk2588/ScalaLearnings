/*
* Recommended techniques for reusing the code in ScalaTest
*     - Refactor using Scala
*     - Override withFixture
*     - Mixin a before and after trait
*
*     get-Fixture methods - This method helps to create a fresh instance of mutable fixture objects in each test that needs them,
*     but does not help clean them up when you are done.
*
*     fixture-content objects: By placing fixture methods and fields into traits, you can easily give each test just the newly
*     created fixture it needs by mixing together traits. Use this technique when you need different combinations of mutable
*     fixture objects in different tests and dont need to cleanup after
*
*     loan-fixture methods : Factor out duplicate code with loan pattern when different tests need different fixtures that
*     must be cleaned up afterwards.
*
*
*     Trait suite implementation of runTest passes a no-arg test function to withFixture(NoArgTest)
* */


package com.packt.examples.loanfixture

import java.io._

import org.scalatest.FlatSpec
import java.util.concurrent.ConcurrentHashMap
import java.util.UUID.randomUUID

import com.packt.examples.loanfixture.DbServer.Db

object DbServer {
  type Db = StringBuffer
  private val databases = new ConcurrentHashMap[String, Db]

  def createDb(name: String): Db = {
    val db = new StringBuffer
    databases.put(name, db)
    db
  }

  def removeDb(name: String): Unit = {
    databases.remove(name)
  }
}

import DbServer._

class LoanFixture extends FlatSpec {

  def withDatabase(testcode: Db => Any): Unit = {
    val dbName = randomUUID.toString
    val db = createDb(dbName)
    //create the fixture
    try {
      db.append("Scalatest is ")
      //perform setup
      testcode(db)
      //loan the fixture to the test
    }

    finally removeDb(dbName)
  }

  def withFile(testCode: (File, FileWriter) => Any): Unit = {
    val file = File.createTempFile("Hello", "World")

    //create the fixture
    val writer = new FileWriter(file)
    try {
      writer.write("Scalatest is ")
      //setup the fixture
      testCode(file, writer)
      //"load" the fixture to the test
    }
    finally writer.close()
  }

  //This test needs the file fixture
    "Testing" should "be productive" in withFile {
    (file, writer) =>
      writer.write("productive")
      writer.flush()
      assert(file.length === 23)
  }edTesting" should "be productive" in withFile {
    (file, writer) =>
      writer.write("productive")
      writer.flush()
      assert(file.length === 23)
  }

  //This test needs the file fixture
  "Test code" should "be readable" in withDatabase {
      db => db.append("readable!")
        assert(db.toString === "Scalatest is readable!")
  }

  it should "be clear and concise" in
  withDatabase {
    db => withFile {
      (file, writer) =>
        db.append("clear!")
        writer.write("concise!")
        writer.flush()
        assert(db.toString === "Scalatest is clear!")
        assert(file.length === 21)
    }
  }
}
