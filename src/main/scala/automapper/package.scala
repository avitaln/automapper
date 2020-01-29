import java.util.UUID

import io.scalaland.chimney.Transformer
import io.scalaland.chimney.dsl._
import io.scalaland.chimney.internal.TransformerCfg

package object automapper {

  def defineMapping[From, To]: TransformerDefinition[From, To, TransformerCfg.Empty] =
     new TransformerDefinition[From, To, TransformerCfg.Empty](Map.empty, Map.empty)

  implicit val uuidToString: Transformer[UUID, String] = (src: UUID) => src.toString
  implicit val stringToUUID: Transformer[String, UUID] = (src: String) => UUID.fromString(src)
  implicit def optToNoOpt[A]: Transformer[Option[A], A] = (src: Option[A]) => src.getOrElse(null).asInstanceOf[A]
  implicit def noOptToOpt[A]: Transformer[A, Option[A]] = (src: A) => Option(src)


  implicit class AutoMapperOps[From](private val source: From) extends AnyVal {
    final def mapTo[To](implicit t: Transformer[From, To]): To = {
      t.transform(source)
    }
  }


}
