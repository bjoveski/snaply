package bjoveski

import scala.collection.mutable

/**
  * Created by bojan on 6/30/16.
  */
trait Util {

  def runAndTime[T](fn: => T): (T, Long) = {
    val start = System.currentTimeMillis()
    val res = fn
    val msTaken = System.currentTimeMillis() - start

    (res, msTaken)
  }

  def zipOption[T, U](t: Seq[T], q: Seq[U]): Seq[(T, Option[U])] = {
    val tt = t.toIterator
    val qq = q.toIterator


    val res = mutable.Buffer.newBuilder[(T, Option[U])]

    while (tt.hasNext && qq.hasNext) {
      res.+=((tt.next(), Some(qq.next())))
    }

    // add the remaining from tt
    while (tt.hasNext) {
      res.+=((tt.next(), None))
    }

    res.result().toSeq
  }
}


trait Colors {

  private def helper(color: String, str: String) = s"$color$str${Console.RESET}"

  def yellow(str: String) = helper(Console.YELLOW, str)
  def green(str: String) = helper(Console.GREEN, str)
  def red(str: String) = helper(Console.RED, str)
}