package controllers

import infos._
import jp.gutio.utils.Json4sUtil
import org.json4s._
import org.json4s.native._
import com.github.tototoshi.play2.json4s.Json4s
import java.time.ZonedDateTime

import play.api.mvc._
import scalikejdbc.{DB, DBSession}

import scala.reflect.ClassTag
import scala.util.control.Exception.allCatch

/**
  * コントローラの共通ロジック
  * Created by Daisuke Yamaguchi on 2017/12/25.
  */
abstract class ControllerBase[AuthType <: AuthenticateInfoBase : ClassTag](cc: ControllerComponents, json4s: Json4s) extends AbstractController(cc) {

  implicit val formats = new DefaultFormats{
    override val typeHintFieldName: String = "class"
    override val typeHints: TypeHints = InfoHints
  } + Json4sUtil.ZDTSerializer

  def Anonymous[ParamType <: InfoBase : ClassTag](func : (ParamType, RequestProperty) => ResponseInfoBase) = Action(json4s.json){implicit request =>
    request.body.extractOpt[EnvelopeInfo] match {
      case None => BadRequest("Not EnvelopInfo")
      case Some(envelope @ EnvelopeInfo(parameter : ParamType,_,_,clientDateTime)) =>
        allCatch either {
          DB localTx { implicit session =>
            val property = RequestProperty(session, ZonedDateTime.now(), clientDateTime)
            func(parameter, property)
          }
        } match {
          // 無事完了
          case Right(result) =>
            Ok(JsonMethods.compact(JsonMethods.render(Extraction.decompose(result))))
          // 将来的にエラーレベルを整理してそれに応じて分岐
          // Asyncを拡張する時には部分関数にして合成するのもアリかも
          case Left(e) =>
            InternalServerError(e.getStackTrace.toString)
        }
      case _ => BadRequest("Un Match Parameter Class")
    }
  }

  // TODO ログや処理実行中に誰の操作かわからなければならないから、戻すのは何かしらのクラスに変更が要りそう
  // トランザクションも引数に取るほうが良さそう
  def auth(authenticate : AuthType) : Boolean

  // エラーハンドリングとかAnonymousとうまく共通化出来ないものか
  def Authenticate[ParamType <: InfoBase : ClassTag](func : (ParamType, RequestProperty) => ResponseInfoBase) = Action(json4s.json){implicit request =>
    request.body.extractOpt[EnvelopeInfo] match {
      case None => BadRequest("Not EnvelopInfo")
      case Some(envelope @ EnvelopeInfo(parameter : ParamType, authenticate : AuthType,_,clientDateTime)) => {
        allCatch either {
          DB localTx { implicit session =>
            if (auth(authenticate)) {
              val property = RequestProperty(session, ZonedDateTime.now(), clientDateTime)
              func(parameter, property)
            }
            else
              throw new Exception("unauthorized")
          }
        } match {
          // 無事完了
          case Right(result) =>
            Ok(JsonMethods.compact(JsonMethods.render(Extraction.decompose(result))))
          // 将来的にエラーレベルを整理してそれに応じて分岐
          // Asyncを拡張する時には部分関数にして合成するのもアリかも
          case Left(e) =>
            InternalServerError(e.getStackTrace.toString)
        }
      }
      case _ => BadRequest("Un Match Envelope Member")
    }
  }
}

case class RequestProperty(session : DBSession, actionNow : ZonedDateTime, clientDateTime : ZonedDateTime)
