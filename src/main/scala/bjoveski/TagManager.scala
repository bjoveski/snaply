package bjoveski

import scala.collection.mutable

/**
  * Created by bojan on 6/30/16.
  */
object TagManager extends Colors {

  def index(images: Seq[TaggedImage]): Map[String, Seq[TaggedImage]] = {
    val store = mutable.Map[String, mutable.Set[TaggedImage]]()

    images.foreach{case image =>
      image.annotations.foreach{case annotation =>
        val k = annotation.getName
        val v = store.getOrElse(k, mutable.Set())
        store.update(k, v + image)
      }
    }

    println(s"created ${store.size} tags")

    store.map{case (k, v) =>
      (k, v.toSeq.sortBy(- _.annotations.find(_.getName == k).get.getProbability))
    }.toMap
  }
}
