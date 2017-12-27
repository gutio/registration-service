package jp.gutio.registration.externals

import com.google.api.client.googleapis.auth.oauth2.{GoogleIdToken, GoogleIdTokenVerifier}
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import jp.gutio.registration.settings.GoogleIdVerifierSetting

import scala.util.control.Exception.allCatch

object GoogleIdVerifier {
  var settingOpt : Option[GoogleIdVerifierSetting] = None
  var clientIds : List[String] = Nil
  var verifier : (String) => GoogleIdToken = (_ : String) => null

  def init(setting : GoogleIdVerifierSetting) = {
    settingOpt = Some(setting)
    clientIds = settingOpt
      .map(setting => List(setting.androidClientId, setting.iosClientId, setting.webClientId).flatten)
      .getOrElse(Nil)
    verifier = {
      if(clientIds.isEmpty) (_ : String) => null
      else {
        val transport = GoogleNetHttpTransport.newTrustedTransport
        val jsonFactory = JacksonFactory.getDefaultInstance
        import collection.JavaConverters._
        val gVerifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory).setAudience(clientIds.asJava).build()
        (idToken : String) => gVerifier.verify(idToken)
      }
    }
  }

  def verify(idToken : String) = allCatch either {
    val gIdToken = verifier(idToken)
    if (gIdToken != null) {
      val payload = gIdToken.getPayload
      if (clientIds.contains(payload.getAuthorizedParty)) {
        payload
      } else {
        throw new Exception
      }
    } else {
      throw new Exception
    }
  }
}