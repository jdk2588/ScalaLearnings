name := "SimpleStreamingExample"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= {
  val akkaV = "2.3.12"
  val akkaStreamV = "2.0-M1"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream-experimental" % akkaStreamV,
    "org.twitter4j" % "twitter4j-stream" % "4.0.3")
}