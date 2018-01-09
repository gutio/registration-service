package jp.gutio.registration.db.query

import jp.gutio.registration.actions.ActionProperty
import jp.gutio.registration.db.model._
import jp.gutio.registration.db.query.QueryBase._

import scalikejdbc._

/**
  * Created by Daisuke Yamaguchi on 2018/1/5.
  */
object AccountQuery extends QueryBase {
  def insert(obj : Account)(implicit property: ActionProperty) =
    inserts(List(obj))

  def inserts(objs : List[Account])(implicit property: ActionProperty) = validUpdate(objs.length, property) {
    if(objs.isEmpty) 0
    withSQL {
      QueryDSL.insert.into(Account).columns(
        Account.column.id,
        Account.column.accessToken,
        Account.column.sessionKey,
        Account.column.createdAt,
        Account.column.updatedAt
      ).multiValues(
        objs.map(_.id),
        objs.map(_.accessToken),
        objs.map(_.sessionKey),
        objs.map(_.createdAt),
        objs.map(_.updatedAt)
      )
    }
  }

  def getById(id : Long)(implicit property: ActionProperty) = get(property) {
    val t = Account.syntax
    withSQL{
      QueryDSL.select.from(Account as t).where.eq(t.id, id)
    }.map(Account.apply(t.resultName))
  }

  def update(obj : Account)(implicit property: ActionProperty) = validUpdate(1, property) {
    withSQL{
      QueryDSL.update(Account).set(
        Account.column.id -> obj.id,
        Account.column.accessToken -> obj.accessToken,
        Account.column.sessionKey -> obj.sessionKey,
        Account.column.createdAt -> obj.createdAt,
        Account.column.updatedAt -> obj.updatedAt
      ).where.eq(Account.column.id, obj.id)
    }
  }

  def deleteById(id : Long)(implicit property: ActionProperty) = validUpdate(1, property) {
    withSQL {
      QueryDSL.delete.from(Account).where.eq(Account.column.id, id)
    }
  }
}
