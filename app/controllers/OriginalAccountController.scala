package controllers

import javax.inject._

import io.circe.generic.auto._
import io.circe.syntax._
import jp.gutio.registration.externals.GoogleIdVerifier
import play.api.mvc._

/**
  * このサービスの独自アカウント関連APIのコントローラ
  *
  * Created by Daisuke Yamaguchi on 2017/12/28.
  */
@Singleton
class OriginalAccountController @Inject()(cc: ControllerComponents) extends BaseController(cc) {

  def create() = Anonymous { implicit request =>
    request.body
    GoogleIdVerifier.settingOpt.asJson
  }
}
