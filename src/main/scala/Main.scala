
import java.util.UUID

import automapper._

case class A(x: UUID)
case class B(x: Option[String], y: B1, z1: String)
case class B1(x1: String)

case class C(x: String, y: C1, z2: String = "")
case class C1(x2: String)

object Main extends App {
  implicit val a2 = defineMapping[B1, C1].withFieldRenamed(_.x1, _.x2).buildTransformer
  val result = B(Some("123"), B1("456"), "z").mapTo[C]
  println(result)
}
