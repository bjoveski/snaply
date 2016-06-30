package bjoveski

/**
  * Hello world!
  *
  */
object Main extends App {
  println( "Hello World!" )

  val reader = new ImageReader()

  val images = reader.readFolder("/Users/bojan/Downloads/test")

  val store = TagManager.index(images.collect{case t: TaggedImage => t})

  store.foreach{case (tag, imgs) =>
      println()
      println(tag)
      imgs.sortBy(_.annotations.find(_.getName == tag).get.getProbability).foreach(t => println(s"\t\t${t.f.getName}"))
  }


  /*
  images.foreach{
    case img: TaggedImage => {
      println(s"${img.f.getName}")
      img.annotations.foreach(annotation => println(s"\t\t$annotation"))
    }
    case img: ErrorImage => {
      println("error!")
    }
  }
  */


}
