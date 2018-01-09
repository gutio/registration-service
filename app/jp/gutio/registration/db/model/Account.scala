package jp.gutio.registration.db.model

import java.time._
import scalikejdbc._

case class Account (
  id : Long,
  accessToken : String,
  sessionKey : String,
  createdAt : ZonedDateTime,
  updatedAt : ZonedDateTime
)

object Account extends SQLSyntaxSupport[Account]{

  override val tableName = "Account"
  override def columns: Seq[String] = Seq("id","accessToken","sessionKey","createdAt","updatedAt")
  override def useSnakeCaseColumnName: Boolean = false

  def apply(g: ResultName[Account])(rs: WrappedResultSet) : Account = {
    Account(
      id = rs.long(g.id),
      accessToken = rs.string(g.accessToken),
      sessionKey = rs.string(g.sessionKey),
      createdAt = rs.zonedDateTime(g.createdAt),
      updatedAt = rs.zonedDateTime(g.updatedAt)
    )
  }
}
