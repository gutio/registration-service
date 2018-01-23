package infos.requests

import controllers.RequestProperty
import infos.{JointInfoBase, RequestInfoBase}
import infos.responses._
import jp.gutio.registration.actions._

case class SessionVerifyRequestInfo(sessionKey : String) extends RequestInfoBase[SessionVerifyResponseInfo] with JointInfoBase {
  override def run()(implicit property: RequestProperty): SessionVerifyResponseInfo = {
    val result = SessionVerifyAction(sessionKey).execute()(property)
    SessionVerifyResponseInfo(result.id)
  }
}

