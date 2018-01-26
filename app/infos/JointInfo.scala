package infos

trait JointRequestInfoBase[T <: ResponseInfoBase] extends RequestInfoBase[T]

case class JointAuthenticateInfo(secret : String) extends AuthenticateInfoBase