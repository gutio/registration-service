package jp.gutio.registration.externals

import javax.inject.{Inject, Singleton}

import jp.gutio.registration.settings.GoogleIdVerifierSetting
import play.api.{Configuration, Environment}
import play.api.inject.{ApplicationLifecycle, Module}

/**
  * Created by Daisuke Yamaguchi on 2017/12/27.
  */
class GoogleIdVerifierModule extends Module {
  override def bindings(environment: Environment, configuration: Configuration) =
    Seq(bind[GoogleIdVerifierInitializer].toSelf.eagerly)
}

@Singleton
class GoogleIdVerifierInitializer @Inject()(config: Configuration, lifecycle: ApplicationLifecycle) {
  config.getOptional[GoogleIdVerifierSetting]("app.GoogleIdVerifier")
    .foreach(GoogleIdVerifier.init(_))
}
