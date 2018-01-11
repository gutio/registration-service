package infos

import controllers.RequestProperty
import jp.gutio.registration.actions.ActionProperty

trait RequestInfoBase[T <: ResponseInfoBase] extends InfoBase {
  def run()(implicit property : RequestProperty) : T

  import scala.language.implicitConversions
  implicit def requestProperty2ActionProperty(property : RequestProperty) : ActionProperty =
    ActionProperty(property.session, property.actionNow, property.clientDateTime)

}
