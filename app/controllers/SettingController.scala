package controllers

import javax.inject._

import play.api.mvc._

import io.circe.generic.auto._
import io.circe.syntax._

import jp.gutio.externals.{GoogleIdVerifier}

/**
 * 設定情報レスポンス用コントローラ
 * TODO 認証
 */
@Singleton
class SettingController @Inject()(cc: ControllerComponents) extends BaseController(cc) {

  def getGoogleSetting() = Action { implicit request: Request[AnyContent] =>
    Ok(GoogleIdVerifier.settingOpt.asJson.noSpaces)
  }
}
