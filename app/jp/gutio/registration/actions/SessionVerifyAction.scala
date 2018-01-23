package jp.gutio.registration.actions

import jp.gutio.registration.db.model.Account
import jp.gutio.registration.db.query.AccountQuery
import jp.gutio.registration.mediators.AccountCreateMediator

/**
  * 独自アカウント作成アクションのロジック
  *
  * Created by Daisuke Yamaguchi on 2018/1/23.
  */
case class SessionVerifyAction(sessionKey : String) extends ActionBase[SessionVerifyActionResult] {
  override def execute()(implicit property: ActionProperty): SessionVerifyActionResult = {
    val account = AccountQuery.getBySessionKey(sessionKey).getOrElse(throw new Exception("Account Not Found"))
    SessionVerifyActionResult(account)
  }
}

/**
  * 結果用データ保持クラス。
  * データ保持がメインなのでファイル分割とりあえず無し。
  *
  * Created by Daisuke Yamaguchi on 2018/1/23.
  */
case class SessionVerifyActionResult(id : Long)
object SessionVerifyActionResult {
  def apply(account : Account) : SessionVerifyActionResult =
    SessionVerifyActionResult(id = account.id)
}
