/**
  * Created by jaideep on 21/06/17.
  */

import akka.actor.ActorSystem
import akka.stream.{ClosedShape, ActorMaterializer}
import akka.stream.javadsl.RunnableGraph
import akka.stream.scaladsl.{Sink, FlowGraph, Flow, Source}
import scala.concurrent.duration._

object Streaming {
  implicit val system = ActorSystem()
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val input = Source(1 to 100)
  val flow = Flow[Int].map(_*2)
  val out = Sink.foreach(println)
//  val in = Source.tick(1.second, 1.second, List(1,2,3).map(i=>i))
//  val doubleFlow = Flow[Int].map(a => a+1)
//  val out = Sink.foreach(println)

  def main(args: Array[String]) {
    input.via(flow).runWith(out)
  }

}