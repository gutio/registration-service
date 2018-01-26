package infos.requests

import controllers.RequestProperty
import infos._
import infos.responses._
import jp.gutio.registration.actions._

case class OriginalAccountCreateRequestInfo() extends RPCRequestInfoBase[OriginalAccountCreateResponseInfo] {
  override def run()(implicit property: RequestProperty): OriginalAccountCreateResponseInfo = {
    val result = OriginalAccountCreateAction().execute()(property)
    OriginalAccountCreateResponseInfo(result.id, result.accessToken)
  }
}

