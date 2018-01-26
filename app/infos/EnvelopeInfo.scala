package infos

import java.time.ZonedDateTime

case class EnvelopeInfo(
  parameter : InfoBase,
  authenticate : AuthenticateInfoBase,
  requestId : String, // 将来的にクライアントでリトライしても多重実行しないための識別子
  clientDateTime : ZonedDateTime
) extends InfoBase

trait AuthenticateInfoBase extends InfoBase
