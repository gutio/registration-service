package infos.requests

import controllers.RequestProperty
import infos._
import infos.responses.MultiCallResponseInfo

case class MultiCallRequestInfo(requests : List[RequestInfoBase[_ <: ResponseInfoBase]]) extends RPCRequestInfoBase[MultiCallResponseInfo] {
  override def run()(implicit property: RequestProperty): MultiCallResponseInfo = {
    MultiCallResponseInfo(requests.map(_.run()))
  }
}

