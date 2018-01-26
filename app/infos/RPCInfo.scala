package infos

trait RPCRequestInfoBase[T <: ResponseInfoBase] extends RequestInfoBase[T]

case class RPCAuthenticateInfo(sessionKey : String) extends AuthenticateInfoBase