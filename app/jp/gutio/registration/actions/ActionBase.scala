package jp.gutio.registration.actions

import java.time.ZonedDateTime

import scalikejdbc.DBSession

/**
  * アプリアクションロジックの基底クラス
  *
  * Created by Daisuke Yamaguchi on 2017/12/27.
  */
trait ActionBase[T] {
  def execute()(implicit property : ActionProperty) : T
}

/**
  * アクション実行時に引き回す環境情報を束ねるクラス。
  *
  * 今後の拡張として、loggerやshard分割に対応したセッション管理クラスの追加や置き換えを検討中。
  *
  * Created by Daisuke Yamaguchi on 2017/12/27.
  */
case class ActionProperty(session : DBSession, actionNow : ZonedDateTime, clientDateTime : ZonedDateTime)
