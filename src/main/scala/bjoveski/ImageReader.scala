package bjoveski

import java.io.File
import java.lang.Integer

import com.clarifai.api.RecognitionRequest
import com.clarifai.api.RecognitionResult.StatusCode

import scala.collection.JavaConverters._

/**
  * Created by bojan on 6/30/16.
  */
class ImageReader extends Util with Colors {

  val BATCH_SIZE = 4

  def readFolder(path: String): Seq[Image] = {
    val folder = new File(path)

    require(folder.isDirectory, s"$path must be a directory!")

    val files = folder.listFiles().filter{f => f.isFile && (f.getName.endsWith(".jpg") || f.getName.endsWith(".png"))}

    files.toSeq.sliding(BATCH_SIZE, BATCH_SIZE).zipWithIndex.toSeq.flatMap{case (slice, idx) =>
      println(f"requesting ${BATCH_SIZE * idx}%4d image.")
      sendRequest(slice)
    }
  }


  def sendRequest(files: Seq[File]): Seq[Image] = {
    val request = new RecognitionRequest(files:_*)
//    request.setModel("food-items-v0.1")

    val (results, ms) = runAndTime {
      val batchResponse = Clarifai.client.recognize(request)

      files.zipWithIndex.map { case (f, idx) =>
        val response = batchResponse.get(idx)

        if (response.getStatusCode == StatusCode.OK) {
          TaggedImage(f, response.getTags.asScala)
        } else {
          ErrorImage(f, response)
        }
      }
    }

    println(green(f"took $ms%5d ms, ${results.count{_.isInstanceOf[TaggedImage]}}%3d/${results.size}%3d"))
    results
  }

}
