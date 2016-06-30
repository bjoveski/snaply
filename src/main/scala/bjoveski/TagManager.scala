package bjoveski

import scala.collection.mutable

/**
  * Created by bojan on 6/30/16.
  */
object TagManager extends Colors {

  def index(images: Seq[TaggedImage]): Map[String, Seq[TaggedImage]] = {
    val store = mutable.Map[String, Seq[TaggedImage]]()

    images.foreach{case image =>
      image.annotations.foreach{case annotation =>
        val k = annotation.getName
        val v = store.getOrElse(k, Seq())
        store.update(k, v ++ Seq(image))
      }
    }

    println(s"created ${store.size} tags")

    store.toMap
  }
}
