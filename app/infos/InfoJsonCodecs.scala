package infos

import io.circe.generic.extras._
import io.circe.generic.semiauto._
import io.circe.java8.time.TimeInstances

class InfoJsonCodecs {

  implicit val infoBaseEncoder = deriveEncoder[InfoBase]
  implicit val infoBaseDecoder = deriveDecoder[InfoBase]

  implicit val responseInfoBaseEncoder = deriveEncoder[ResponseInfoBase]
  implicit val responseInfoBaseDecoder = deriveDecoder[ResponseInfoBase]

  implicit def requestInfoBaseEncoder[T] = deriveEncoder[RequestInfoBase[T]]
  implicit def requestInfoBaseDecoder[T] = deriveDecoder[RequestInfoBase[T]]

  implicit val restEnvelopeEncoder = deriveEncoder[RestEnvelopeInfo]
  implicit val restEnvelopeDecoder = deriveDecoder[RestEnvelopeInfo]
}
