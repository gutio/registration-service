package jp.gutio.utils

import org.json4s._
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Json4sUtil {
  case object ZDTSerializer extends CustomSerializer[ZonedDateTime](format => ( {
    case JString(s) => ZonedDateTime.parse(s)
  }, {
    case zdt: ZonedDateTime => JString(zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")))
  }))
}
