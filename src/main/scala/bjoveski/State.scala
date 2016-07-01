package bjoveski

import java.io.File

/**
  * Created by bojan on 7/1/16.
  */
case class State(store: Map[File, TaggedImage], index: Map[String, Seq[TaggedImage]])

object State {

  def apply(): State = new State(Map(), Map())

  def apply(dir: File) = {
    val images = ImageReader.readFolder(dir.getAbsolutePath)
    val taggedImages = images.collect{case ti: TaggedImage => ti}
    println(f"tagged ${taggedImages.size}%4d/${images.size} images")

    val store = taggedImages.map{img => img.f -> img}.toMap
    val index = TagManager.index(taggedImages)

    new State(store, index)
  }
}