package jp.gutio.registration.db.query

import jp.gutio.registration.actions.ActionProperty
import jp.gutio.registration.db.model._
import jp.gutio.registration.db.query.QueryBase._

import scalikejdbc._

/**
  * Created by Daisuke Yamaguchi on 2018/1/5.
  */
object GoogleAccountQuery extends QueryBase {
  def insert(obj : GoogleAccount)(implicit property: ActionProperty) =
    inserts(List(obj))

  def inserts(objs : List[GoogleAccount])(implicit property: ActionProperty) = validUpdate(objs.length, property) {
    if(objs.isEmpty) 0
    withSQL{
      QueryDSL.insert.into(GoogleAccount).columns(
        GoogleAccount.column.accountId,
        GoogleAccount.column.googleId,
        GoogleAccount.column.gmailAddress,
        GoogleAccount.column.createdAt,
        GoogleAccount.column.updatedAt
      ).multiValues(
        objs.map(_.accountId),
        objs.map(_.googleId),
        objs.map(_.gmailAddress),
        objs.map(_.createdAt),
        objs.map(_.updatedAt)
      )
    }
  }

  def getByAccountId(accountId : Long)(implicit property: ActionProperty) = get(property){
    val t = GoogleAccount.syntax
    withSQL {
      QueryDSL.select.from(GoogleAccount as t).where.eq(t.accountId, accountId)
    }.map(GoogleAccount.apply(t.resultName))
  }

  def getByGoogleId(googleId : String)(implicit property: ActionProperty) = get(property){
    val t = GoogleAccount.syntax
    withSQL {
      QueryDSL.select.from(GoogleAccount as t).where.eq(t.googleId, googleId)
    }.map(GoogleAccount.apply(t.resultName))
  }


  def update(obj : GoogleAccount)(implicit property: ActionProperty) = validUpdate(1, property) {
    withSQL{
      QueryDSL.update(GoogleAccount).set(
        GoogleAccount.column.accountId -> obj.accountId,
        GoogleAccount.column.googleId -> obj.googleId,
        GoogleAccount.column.gmailAddress -> obj.gmailAddress,
        GoogleAccount.column.createdAt -> obj.createdAt,
        GoogleAccount.column.updatedAt -> obj.updatedAt
      ).where.eq(GoogleAccount.column.accountId, obj.accountId)
    }
  }

  def deleteByAccountId(accountId : Long)(implicit property: ActionProperty) = validUpdate(1, property) {
    withSQL {
      QueryDSL.delete.from(GoogleAccount).where.eq(GoogleAccount.column.accountId, accountId)
    }
  }

  def deleteByGoogleId(googleId : String)(implicit property: ActionProperty) = validUpdate(1, property) {
    withSQL {
      QueryDSL.delete.from(GoogleAccount).where.eq(GoogleAccount.column.googleId, googleId)
    }
  }
}
