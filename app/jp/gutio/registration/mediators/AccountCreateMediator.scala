package jp.gutio.registration.mediators

import jp.gutio.registration.actions.ActionProperty
import jp.gutio.registration.db.model.Account
import jp.gutio.registration.db.query.AccountQuery
import jp.gutio.utils.RandomUtil

import scalikejdbc._

/**
  * アカウント発行Mediator。
  * 現状、同時に実行するとUnique制約違反でコケるトランザクションを制御する。
  */
object AccountCreateMediator {
  def create()(implicit property: ActionProperty) = {
    this.synchronized {
      implicit val session = property.session
      // 将来的にはAutoIncrementを使わずにId発行する部分だけをRedisなどに切り出してはどうか
      val id = withSQL{
        QueryDSL.insert.into(Account).namedValues(
          Account.column.accessToken -> "",
          Account.column.sessionKey -> "",
          Account.column.createdAt -> property.actionNow,
          Account.column.updatedAt -> property.actionNow
        )
      }.updateAndReturnGeneratedKey.apply()
      // 今後sharding時にアクセスしやすくすることを考えてトークンにid文字列を追加
      val accessToken = RandomUtil.random.alphanumeric.take(12).mkString + "-" + id
      val account = Account(id, accessToken, "", property.actionNow, property.actionNow)
      AccountQuery.update(account)
      account
    }
  }
}
