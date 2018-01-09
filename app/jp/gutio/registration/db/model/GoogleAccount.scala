package jp.gutio.registration.db.model

import java.time._
import scalikejdbc._

case class GoogleAccount (
  accountId : Long,
  googleId : String,
  gmailAddress : String,
  createdAt : ZonedDateTime,
  updatedAt : ZonedDateTime
)

object GoogleAccount extends SQLSyntaxSupport[GoogleAccount]{

  override val tableName = "GoogleAccount"
  override def columns: Seq[String] = Seq("accountId","googleId","gmailAddress","createdAt","updatedAt")
  override def useSnakeCaseColumnName: Boolean = false

  def apply(g: ResultName[GoogleAccount])(rs: WrappedResultSet) : GoogleAccount = {
    GoogleAccount(
      accountId = rs.long(g.accountId),
      googleId = rs.string(g.googleId),
      gmailAddress = rs.string(g.gmailAddress),
      createdAt = rs.zonedDateTime(g.createdAt),
      updatedAt = rs.zonedDateTime(g.updatedAt)
    )
  }
}
