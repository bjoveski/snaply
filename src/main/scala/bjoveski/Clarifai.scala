package bjoveski

import java.io.File

import com.clarifai.api.{ClarifaiClient, RecognitionRequest, RecognitionResult, Tag}
import scala.collection.JavaConverters._

/**
  * Created by bojan on 6/30/16.
  */
object Clarifai {

  val APP_ID = sys.env("CLARIFAI_APP_ID")
  val APP_SECRET = sys.env("CLARIFAI_APP_SECRET")

  lazy val client = new ClarifaiClient(APP_ID, APP_SECRET)

  def run() = {

    val results = client.recognize(new RecognitionRequest(new File("/Users/bojan/Downloads/gosaikunda.jpg")))


    results.get(0).getTags.asScala.foreach{tag =>

      println(tag.getName + ": " + tag.getProbability)
    }
  }

}
