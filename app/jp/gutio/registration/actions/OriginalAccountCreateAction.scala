package jp.gutio.registration.actions

import jp.gutio.registration.db.model.Account

/**
  * 独自アカウント作成アクションのロジック
  *
  * Created by Daisuke Yamaguchi on 2017/12/28.
  */
case class OriginalAccountCreateAction() extends ActionBase[OriginalAccountCreateActionResult] {
  override def execute()(implicit property: ActionProperty): OriginalAccountCreateActionResult = {
    // TODO 実装
    ???
  }
}

/**
  * 結果用データ保持クラス。
  * データ保持がメインなのでファイル分割とりあえず無し。
  *
  * データ項目に関連DBテーブルクラスのまま保持してしまうかは迷い中。
  *
  * Created by Daisuke Yamaguchi on 2017/12/28.
  */
case class OriginalAccountCreateActionResult()
object OriginalAccountCreateActionResult {
  def apply(account : Account) : OriginalAccountCreateActionResult =
    OriginalAccountCreateActionResult()
}
