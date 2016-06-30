package bjoveski

import java.io.File

import com.clarifai.api.{RecognitionResult, Tag}

/**
  * Created by bojan on 6/30/16.
  */

sealed trait Image

case class TaggedImage(f: File, annotations: Seq[Tag]) extends Image
case class ErrorImage(f: File, response: RecognitionResult) extends Image


