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
  import com.typesafe.config.ConfigFactory
  val config: Config = ConfigFactory.parseString(conf)
  val system = ActorSystem("hurricane", config)
  val FirstActor = system.actorOf(Props[FirstActor], name = "firstactor")
  val SecondActor = system.actorOf(Props[SecondActor], name = "secondactor")
  FirstActor.tell("mx", SecondActor)
}