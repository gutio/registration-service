package controllers

import play.api.mvc.{AbstractController, ControllerComponents}

/**
  * コントローラの共通ロジック
  * Created by Daisuke Yamaguchi on 2017/12/25.
  */
abstract class ControllerBase(cc: ControllerComponents) extends AbstractController(cc) {

}
