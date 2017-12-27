package jp.gutio.registration.settings

import com.typesafe.config.Config
import play.api.ConfigLoader

/**
  * Created by Daisuke Yamaguchi on 2017/12/25.
  */
trait BaseConfigLoader[T] extends ConfigLoader[T] {
  def wrapOpt[A](config : Config, path : String)(func : (Config, String) => A) : Option[A] = {
    if(config.hasPath(path))
      Some(func(config, path))
    else
      None
  }
}
