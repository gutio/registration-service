import io.circe.generic.semiauto._
import controllers.RequestProperty

package object infos {

  sealed trait InfoBase
  sealed trait ResponseInfoBase extends InfoBase

  sealed trait RequestInfoBase[T >: ResponseInfoBase] extends InfoBase {
    def run()(implicit property : RequestProperty) : T
  }
  case class RestEnvelopeInfo(params : List[InfoBase]) extends InfoBase

  object requests {
    import responses._

    class OriginalAccountCreateRequestInfo extends RequestInfoBase[OriginalAccountCreateResponseInfo] {
      override def run()(implicit property: RequestProperty): OriginalAccountCreateResponseInfo = {
        // TODO Action呼び出し
        OriginalAccountCreateResponseInfo(1, "abcdef")
      }
    }
  }

  object responses {
    case class OriginalAccountCreateResponseInfo(id : Long, accessToken : String) extends ResponseInfoBase
  }
}
