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

  store.foreach{case (k, imgs) =>
      println(f"$k\t\t\t${imgs.size}%3d")
      imgs.foreach(img => println(s"\t\t${img.annotations.find(_.getName == k).get.getProbability}\t${img.f.getName}"))

  }


//  images.foreach{
//    case img: TaggedImage => {
//      println(s"${img.f.getName}")
//      img.annotations.foreach(annotation => println(s"\t\t$annotation"))
//    }
//    case img: ErrorImage => {
//      println("error!")
//    }
//  }

}
