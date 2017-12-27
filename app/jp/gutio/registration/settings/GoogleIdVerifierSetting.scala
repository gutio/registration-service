package jp.gutio.registration.settings

import com.typesafe.config.Config

case class GoogleIdVerifierSetting(
  iosClientId : Option[String],
  androidClientId : Option[String],
  webClientId : Option[String]
)

object GoogleIdVerifierSetting {
  implicit val loader =
    new BaseConfigLoader[GoogleIdVerifierSetting] {
      def load(rootConfig: Config, path: String): GoogleIdVerifierSetting = {
        val config = rootConfig.getConfig(path)
        GoogleIdVerifierSetting(
          iosClientId = wrapOpt(config, "iosClientId"){_.getString(_)},
          androidClientId = wrapOpt(config, "androidClientId"){_.getString(_)},
          webClientId = wrapOpt(config, "webClientId"){_.getString(_)}
        )
      }
    }
  val empty = GoogleIdVerifierSetting(None, None, None)
}
