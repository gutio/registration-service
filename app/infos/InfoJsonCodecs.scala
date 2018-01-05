package infos

import io.circe._
import io.circe.generic.extras._
import io.circe.generic.semiauto._
import io.circe.java8.time.TimeInstances

object InfoJsonCodecs {

  implicit val restEnvelopeEncoder: Encoder[RestEnvelopeInfo] = deriveEncoder
  implicit val restEnvelopeDecoder: Decoder[RestEnvelopeInfo] = deriveDecoder

  private object autoDeri extends AutoDerivation with TimeInstances {
    import shapeless._
    // all the custom codecs ...
    implicit val configuration: Configuration = Configuration.default
      .withDiscriminator("type")
  }
}
