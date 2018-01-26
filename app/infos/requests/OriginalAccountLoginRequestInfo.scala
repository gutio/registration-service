package infos.requests

import controllers.RequestProperty
import infos._
import infos.responses._
import jp.gutio.registration.actions._

case class OriginalAccountLoginRequestInfo(accessToken : String) extends RequestInfoBase[OriginalAccountLoginResponseInfo] {
  override def run()(implicit property: RequestProperty): OriginalAccountLoginResponseInfo = {
    val result = OriginalAccountLoginAction(accessToken).execute()(property)
    OriginalAccountLoginResponseInfo(result.sessionKey)
  }
}
