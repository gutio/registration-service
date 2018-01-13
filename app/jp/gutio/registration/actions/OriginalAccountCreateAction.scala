package jp.gutio.registration.actions

import jp.gutio.registration.db.model.Account
import jp.gutio.registration.mediators.AccountCreateMediator

/**
  * 独自アカウント作成アクションのロジック
  *
  * Created by Daisuke Yamaguchi on 2017/12/28.
  */
case class OriginalAccountCreateAction() extends ActionBase[OriginalAccountCreateActionResult] {
  override def execute()(implicit property: ActionProperty): OriginalAccountCreateActionResult = {
    val account = AccountCreateMediator.create()
    OriginalAccountCreateActionResult(account)
  }
}

/**
  * 結果用データ保持クラス。
  * データ保持がメインなのでファイル分割とりあえず無し。
  *
  * Created by Daisuke Yamaguchi on 2017/12/28.
  */
case class OriginalAccountCreateActionResult(id : Long, accessToken : String)
object OriginalAccountCreateActionResult {
  def apply(account : Account) : OriginalAccountCreateActionResult =
    OriginalAccountCreateActionResult(id = account.id, accessToken = account.accessToken)
}
