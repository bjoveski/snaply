package bjoveski

import com.clarifai.api.ClarifaiClient

/**
  * Created by bojan on 6/30/16.
  */
object Clarifai {

  val APP_ID = sys.env("CLARIFAI_APP_ID")
  val APP_SECRET = sys.env("CLARIFAI_APP_SECRET")

  lazy val client = new ClarifaiClient(APP_ID, APP_SECRET)

}
