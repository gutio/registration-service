package jp.gutio.registration.db.query

import jp.gutio.registration.actions.ActionProperty
import scalikejdbc._

/**
  * Created by Daisuke Yamaguchi on 2018/1/8.
  */
trait QueryBase {
  def validUpdate[A](assertion : Int, property: ActionProperty)(query : SQL[A, NoExtractor]) = {
    implicit val session = property.session
    val res = query update() apply()
    if(res != assertion) throw new BadCountUpdateRecords
  }

  def get[A](property: ActionProperty)(query : SQL[A, HasExtractor]) = {
    implicit val session = property.session
    query.headOption.apply()
  }
  def getList[A](property: ActionProperty)(query : SQL[A, HasExtractor]) = {
    implicit val session = property.session
    query.list().apply()
  }
}

object QueryBase {
  /**
    * バルクインサート
    * http://d.hatena.ne.jp/ponkotuy/20140305/1394048284
    * @param self
    */
  implicit class BulkInsertSQLBuilder(val self: InsertSQLBuilder) extends AnyVal {
    def multiValues(values: Seq[Any]*): InsertSQLBuilder = {
      values.foreach(x => require(x.nonEmpty))
      val elems = values.transpose.map { xs =>
        val ys = xs.map(x => sqls"$x")
        sqls"(${sqls.csv(ys: _*)})"
      }
      self.append(sqls"values ${sqls.csv(elems: _*)}")
    }
  }
}

/**
  * InsertやUpdateでの変更件数が想定外になったエラー
  */
class BadCountUpdateRecords extends Exception
