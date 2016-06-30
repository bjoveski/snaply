package bjoveski

/**
  * Hello world!
  *
  */
object Main extends App {
  println( "Hello World!" )

  val reader = new ImageReader()

  val images = reader.readFolder("/Users/bojan/Downloads/test")

  images.foreach{
    case img: TaggedImage => {
      println(s"${img.f.getName}")
      img.annotations.foreach(annotation => println(s"\t\t$annotation"))
    }
    case img: ErrorImage => {
      println("error!")
    }

  }


}
