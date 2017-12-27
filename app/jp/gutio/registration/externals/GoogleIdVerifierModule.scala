package jp.gutio.registration.externals

import javax.inject.{Inject, Singleton}

import jp.gutio.registration.settings.GoogleIdVerifierSetting
import play.api.{Configuration, Environment}
import play.api.inject.{ApplicationLifecycle, Module}

class GoogleIdVerifierModule extends Module {
  def bindings(environment: Environment, configuration: Configuration) =
    Seq(bind[GoogleIdVerifierInitializer].toSelf.eagerly)
}

@Singleton
class GoogleIdVerifierInitializer @Inject()(config: Configuration, lifecycle: ApplicationLifecycle) {
  config.getOptional[GoogleIdVerifierSetting]("app.GoogleIdVerifier")
    .foreach(GoogleIdVerifier.init(_))
}
