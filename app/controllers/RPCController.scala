package controllers

import javax.inject._

import com.github.tototoshi.play2.json4s.Json4s
import play.api.mvc._

import infos._

/**
  *
  * Created by Daisuke Yamaguchi on 2018/1/10.
  */
@Singleton
class RPCController @Inject()(cc: ControllerComponents, json4s: Json4s) extends ControllerBase(cc, json4s) {

  def call() = Anonymous[RequestInfoBase[_ <: ResponseInfoBase]] {(request, property) =>
    request.run()(property)
  }
}
