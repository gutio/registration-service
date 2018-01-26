package controllers

import java.time.ZonedDateTime
import javax.inject._

import com.github.tototoshi.play2.json4s.Json4s
import infos._
import jp.gutio.utils.Json4sUtil
import org.json4s.native.JsonMethods
import org.json4s.{DefaultFormats, Extraction, TypeHints}
import play.api.mvc._
import scalikejdbc.DB

import scala.util.control.Exception.allCatch

/**
  *
  * Created by Daisuke Yamaguchi on 2018/1/23.
  */
@Singleton
class JointController @Inject()(cc: ControllerComponents, json4s: Json4s) extends ControllerBase[JointAuthenticateInfo](cc, json4s) {

  // とりあえずsecretをマジックナンバーで埋め込み。
  // 将来的にはDBに保持したいから設定ファイルから読み出すModule化は保留
  override def auth(authenticate : JointAuthenticateInfo) = List("secret1, secret2").contains(authenticate.secret)

  def call() = Authenticate[RequestInfoBase[_ <: ResponseInfoBase]] {(request, property) =>
    request.run()(property)
  }
}
