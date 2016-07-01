package ui

import java.io.File

import bjoveski.State

import scala.swing.GridBagPanel.{Anchor, Fill}
import scala.swing._


/**
  * Created by bojan on 6/30/16.
  */

object Window extends SimpleSwingApplication {

  var currentDir: Option[File] = None

  var state = State()

  //  var currentImage: Option[Image] = None

  def getDirectoryListing(): Option[File] = {
    val chooser = new FileChooser(null)
    chooser.fileSelectionMode = FileChooser.SelectionMode.DirectoriesOnly
    chooser.title = "Please choose a directory"
    val result = chooser.showOpenDialog(null)
    if (result == FileChooser.Result.Approve) {
      Some(chooser.selectedFile)
    } else {
      None
    }
  }

  def buttons = Seq(grid.button1, grid.button2, grid.button3)
  def imagePanels = Seq(grid.img1, grid.img2, grid.img3)

  def onButtonClick(button: Button) = {
    val tag = button.text
    val images = state.store.getOrElse(tag, Seq()).take(3)

    imagePanels.foreach(_.reset(None))

    images.zip(imagePanels).foreach{case (taggedImage, imagePanel) =>
      imagePanel.reset(Some(taggedImage.f))
    }

  }

  val grid = new GridBagPanel {
    val c = new Constraints
    val shouldFill = true
    if (shouldFill) {
      c.fill = Fill.Horizontal
    }

    // tags
    val button1 = new Button()

    c.weightx = 0.33

    c.fill = Fill.Horizontal
    c.gridx = 0
    c.gridy = 0
    layout(button1) = c

    val button2 = new Button()
    c.fill = Fill.Horizontal
    c.weightx = 0.33
    c.gridx = 1
    c.gridy = 0
    layout(button2) = c

    val button3 = new Button()
    c.fill = Fill.Horizontal
    c.weightx = 0.33
    c.gridx = 2
    c.gridy = 0
    layout(button3) = c

    val mainImage = new ImagePanel { imagePath = None }
    c.fill = Fill.Horizontal
    c.ipadx = 800
    c.ipady = 600      //make this component tall
    c.weightx = 0.0
    c.gridwidth = 3    //3 columns wide
    c.gridx = 0
    c.gridy = 1
    layout(mainImage) = c


    val img1 = new ImagePanel { imagePath = None }
    c.fill = Fill.Horizontal
    c.ipady = 0       //reset to default
    c.weighty = 1.0   //request any extra vertical space
    c.anchor = Anchor.PageEnd
    c.insets = new Insets(10,0,0,0)  //top padding
    c.gridx = 0       //aligned with button 2
    c.gridy = 2       //third row
    layout(img1) = c


    val img2 = new ImagePanel { imagePath = None }
    c.fill = Fill.Horizontal
    c.ipady = 0       //reset to default
    c.weighty = 1.0   //request any extra vertical space
    c.anchor = Anchor.PageEnd
    c.insets = new Insets(10,0,0,0)  //top padding
    c.gridx = 1       //aligned with button 2
    c.gridy = 2       //third row
    layout(img2) = c

    val img3 = new ImagePanel { imagePath = None }
    c.fill = Fill.Horizontal
    c.ipady = 0       //reset to default
    c.weighty = 1.0   //request any extra vertical space
    c.anchor = Anchor.PageEnd
    c.insets = new Insets(10,0,0,0)  //top padding
    c.gridx = 2       //aligned with button 2
    c.gridy = 2       //third row
    layout(img3) = c
  }


  def top: Frame = new MainFrame {

    title = "Snaply"

    contents = grid

    listenTo(buttons:_*)
    listenTo(imagePanels:_*)

    currentDir = getDirectoryListing()

    state = currentDir.map(State.apply(_)).getOrElse(state)

    val img = state.store.head._2.head
    grid.mainImage.reset(img.f)

    buttons.zip(img.annotations).foreach{case (button, tag) => button.text = tag.getName}

//    listenTo(button)
//    listenTo(img)



    //    reactions += {
    //      case ButtonClicked(component) if component == button => {
    //        currentDir = getDirectoryListing()
    //        currentImage = currentDir.flatMap(_.listFiles().find{f => f.isFile && (f.getName.endsWith(".jpg") || f.getName.endsWith(".png"))})
    //        img.reset(currentImage.map(_.getAbsolutePath))
    //      }

    //      case ButtonClicked(component) if component == toggle =>
    //        toggle.text = if (toggle.selected) "On" else "Off"
    //      case MouseClicked(_, point, _, _, _) =>
    //        //        canvas.throwDart(new Dart(point.x, point.y, Color.black))
    //        textField.text = (s"You clicked in the Canvas at x=${point.x}, y=${point.y}.")
  }


}




