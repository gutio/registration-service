package jp.gutio.utils

import java.security.SecureRandom

object RandomUtil {
  // 乱数生成器を指定
  // https://gist.github.com/buzztaiki/ca89a079b093d716ef94
  val random = new scala.util.Random(SecureRandom.getInstance("NativePRNGNonBlocking"))
}
