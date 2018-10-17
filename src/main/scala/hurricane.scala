import java.time.{Duration, Period}
import java.time.temporal.TemporalAmount
import java.{lang, util}
import java.util.Map
import java.util.concurrent.TimeUnit

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import com.typesafe.config._

class FirstActor extends Actor {
  def receive = {

    case x:String       => {println(x+"==>Just received     "+self.path)
                    sender!"received"}
  }
}

class SecondActor extends Actor {
  def receive = {
    case f      => println(self + "==>Just received   "+ f)
  }
}

object Main extends App {

  val conf = """
  akka {
   actor {
     provider = remote
   }
   remote {
     artery {
       enabled = on
       transport = aeron-udp
       canonical.hostname = "127.0.0.1"
       canonical.port = 25555
     }
   }
 }
"""

 // ConfigFactory.parseString(conf)
 // ConfigFactory.load()

  import com.typesafe.config.ConfigFactory
  val config: Config = ConfigFactory.parseString(conf)



  import com.typesafe.config.ConfigFactory

  val regularConfig = ConfigFactory.load
  // override regular stack with myConfig
  val combined = config.withFallback(regularConfig)

  ConfigFactory.load


  val system = ActorSystem("hurricane", config)



  //system.logConfiguration()
  // default Actor constructor
  val FirstActor = system.actorOf(Props[FirstActor], name = "firstactor")
  val SecondActor = system.actorOf(Props[SecondActor], name = "secondactor")

  FirstActor.tell("mx", SecondActor)




}