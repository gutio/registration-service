package controllers

import java.time.ZonedDateTime

import infos._
import play.api.libs.circe.Circe
import play.api.mvc._
import io.circe.syntax._

import scala.util.control.Exception.allCatch

/**
  * コントローラの共通ロジック
  * Created by Daisuke Yamaguchi on 2017/12/25.
  */
class BaseController(cc: ControllerComponents) extends AbstractController(cc) with Circe {

  def Anonymous[T >: ResponseInfoBase](func: Request[RestEnvelopeInfo] => T) = Action(circe.json[RestEnvelopeInfo]) { implicit request =>
    // TODO 認証
    // TODO エラーハンドリングで囲う
    allCatch either {
      func(request)
    } match {
      case Right(result) => Ok(result.asJson)
      case Left(e) => InternalServerError(e.getStackTrace.toString) // とりあえずStackTrace
    }
  }

  def Authenticate() = {

  }

}

case class RequestProperty(clientDateTime : ZonedDateTime)
