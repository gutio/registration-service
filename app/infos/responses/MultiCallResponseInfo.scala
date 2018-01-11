package infos.responses

import infos.ResponseInfoBase

case class MultiCallResponseInfo(responses : List[_ <: ResponseInfoBase]) extends ResponseInfoBase {

}
