package controllers

import javax.inject._

import play.api.mvc._
import com.github.tototoshi.play2.json4s.Json4s
import org.json4s._

import jp.gutio.registration.externals.GoogleIdVerifier
import jp.gutio.registration.settings.GoogleIdVerifierSetting

/**
  * 設定情報レスポンス用コントローラ
  * TODO 認証
  * Created by Daisuke Yamaguchi on 2017/12/25.
  */
@Singleton
class SettingController @Inject()(cc: ControllerComponents, json4s: Json4s) extends AbstractController(cc) {

  import json4s.implicits._
  implicit val formats = DefaultFormats

  def getGoogleSetting() = Action { implicit request: Request[AnyContent] =>
    val setting = GoogleIdVerifier.settingOpt.getOrElse(GoogleIdVerifierSetting.empty)
    Ok(Extraction.decompose(setting))
  }
}
