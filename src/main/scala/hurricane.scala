import java.io.File
import java.nio.file.{Files, Path, Paths}

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
  import com.typesafe.config.ConfigFactory
  val setts = new String(Files.readAllBytes(new File("./config.conf").toPath))
  val config: Config = ConfigFactory.parseString(setts)
  val system = ActorSystem("hurricane", config)
  val FirstActor = system.actorOf(Props[FirstActor], name = "firstactor")
  val SecondActor = system.actorOf(Props[SecondActor], name = "secondactor")
  FirstActor.tell("mx", SecondActor)
}