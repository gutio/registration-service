import infos.requests._
import infos.responses._
import org.json4s.TypeHints

package object infos {

  // インスタンス化するもののみ列挙
  // そのうちInfoとまとめて自動生成したほうが良さそう
  val infoClasses = List(
    // 基本
    classOf[EnvelopeInfo],
    classOf[MultiCallRequestInfo],
    classOf[MultiCallResponseInfo],
    // 個別RPC
    classOf[OriginalAccountCreateRequestInfo],
    classOf[OriginalAccountCreateResponseInfo]
  )

  object InfoHints extends TypeHints {
    override def hintFor(clazz: Class[_]): String = {
      val result = classToHint.getOrElse(clazz, "unknown")
      result
    }

    override def classFor(hint: String): Option[Class[_]] =
      hintToClass.get(hint)

    override val hints = infoClasses

    val classToHint: Map[Class[_], String] = {
      infoClasses.map(c => c -> c.getSimpleName).toMap
    }

    val hintToClass: Map[String, Class[_]] = {
      infoClasses.map(c => c.getSimpleName -> c).toMap
    }
  }
}
