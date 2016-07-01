package ui


import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import scala.swing.Panel

/**
  * Created by bojan on 7/1/16.
  */
class ImagePanel extends Panel {
  private var _imageFile: Option[File] = None
  private var bufferedImage: Option[BufferedImage] = None

  def imagePath = _imageFile

  def imagePath_=(value: Option[File]) {
    _imageFile = value
    bufferedImage = _imageFile.map(file => ImageIO.read(file))
  }

  override def paintComponent(g:Graphics2D) = {
    bufferedImage.foreach(g.drawImage(_, 0, 0, math.max(0, size.width - 10), math.max(0, size.height - 10), null))
  }

  override def ignoreRepaint: Boolean = false

//  def reset(file: File): Unit = resetHelper(Some(file))
//  def reset(): Unit = resetHelper(None)

  def reset(file: Option[File]): Unit = {
    imagePath_=(file)
    repaint()
  }
}

object ImagePanel {
  def apply() = new ImagePanel()
}