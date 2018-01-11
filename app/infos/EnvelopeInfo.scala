package infos

import java.time.ZonedDateTime

case class EnvelopeInfo(
  parameter : InfoBase,
  accessToken : String,
  sessionKey : String,
  requestId : String, // 将来的にクライアントでリトライしても多重実行しないための識別子
  clientDateTime : ZonedDateTime
) extends InfoBase
