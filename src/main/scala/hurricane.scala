import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

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
  val system = ActorSystem("hurricane")
  //system.logConfiguration()
  // default Actor constructor
  val FirstActor = system.actorOf(Props[FirstActor], name = "firstactor")
  val SecondActor = system.actorOf(Props[SecondActor], name = "secondactor")

  FirstActor.tell("mx", SecondActor)




}