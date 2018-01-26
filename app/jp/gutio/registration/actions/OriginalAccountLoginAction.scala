package jp.gutio.registration.actions

import jp.gutio.registration.db.model.Account
import jp.gutio.registration.db.query.AccountQuery
import jp.gutio.utils.RandomUtil

/**
  * 独自アカウントログインアクションのロジック
  *
  * Created by Daisuke Yamaguchi on 2018/1/23.
  */
case class OriginalAccountLoginAction(accessToken : String) extends ActionBase[OriginalAccountLoginActionResult] {
  override def execute()(implicit property: ActionProperty): OriginalAccountLoginActionResult = {
    val account = AccountQuery.getByAccessTokenForUpdate(accessToken).getOrElse(throw new Exception("Account Not Found"))
    // 今後sharding時にアクセスしやすくすることを考えてトークンにid文字列を追加
    val sessionKey = RandomUtil.random.alphanumeric.take(12).mkString + "-" + account.id
    val newAccount = account.copy(sessionKey = sessionKey, updatedAt = property.actionNow)
    AccountQuery.update(newAccount)

    OriginalAccountLoginActionResult(newAccount)
  }
}

/**
  * 結果用データ保持クラス。
  * データ保持がメインなのでファイル分割とりあえず無し。
  *
  * Created by Daisuke Yamaguchi on 2018/1/23.
  */
case class OriginalAccountLoginActionResult(sessionKey : String)
object OriginalAccountLoginActionResult {
  def apply(account : Account) : OriginalAccountLoginActionResult =
    OriginalAccountLoginActionResult(sessionKey = account.sessionKey)
}
