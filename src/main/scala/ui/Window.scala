package ui

import java.io.File

import bjoveski.{State, Util}

import scala.swing.GridBagPanel.Fill
import scala.swing._
import scala.swing.event.{ButtonClicked, MouseClicked}
import scala.util.Random


/**
  * Created by bojan on 6/30/16.
  */

object Window extends SimpleSwingApplication with Util {

  var currentDir: Option[File] = None

  var state = State()

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

  val grid = new GridBagPanel {
    val c = new Constraints
    val shouldFill = true
    if (shouldFill) {
      c.fill = Fill.Both
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
    c.fill = Fill.Both
    c.ipadx = 10
    c.ipady = 10      //make this component tall
    c.weighty = 1.0   //request any extra vertical space
    c.gridwidth = 3    //3 columns wide
    c.gridx = 0
    c.gridy = 1
    layout(mainImage) = c


    val img1 = new ImagePanel { imagePath = None }
    c.fill = Fill.Horizontal
    c.ipady = 0       //reset to default
    c.gridwidth = 1
    c.weightx = 0.33
    c.weighty = 0.5
    c.insets = new Insets(4,4,4,4)  //padding
    c.gridx = 0
    c.gridy = 2       //third row
    layout(img1) = c


    val img2 = new ImagePanel { imagePath = None }
    c.fill = Fill.Both
    c.ipady = 0       //reset to default
    c.gridwidth = 1
    c.weightx = 0.33
    c.weighty = 0.5
    c.insets = new Insets(4,4,4,4)  //padding
    c.gridx = 1
    c.gridy = 2       //third row
    layout(img2) = c

    val img3 = new ImagePanel { imagePath = None }
    c.fill = Fill.Both
    c.ipady = 0       //reset to default
    c.gridwidth = 1
    c.weightx = 0.33
    c.weighty = 0.5
    c.insets = new Insets(4,4,4,4)  //padding
    c.gridx = 2
    c.gridy = 2       //third row
    layout(img3) = c
  }


  def top: Frame = new MainFrame {

    title = "Snaply"

    preferredSize = new Dimension(800, 600)

    contents = grid

    // initialization work
    currentDir = getDirectoryListing()
    state = currentDir.map(State.apply(_)).getOrElse(state)
    val img = state.index.head._2.head

    grid.mainImage.reset(Some(img.f))

    buttons.zip(img.annotations).foreach{case (button, tag) => button.text = tag.getName}


    listenTo(buttons:_*)
    listenTo(imagePanels.map(_.mouse.clicks):_*)

    reactions += {
      // reset images
      case ButtonClicked(component) if buttons.contains(component) => {
        val tag = component.text
        val newImages = Random.shuffle(state.index(tag))

        zipOption(imagePanels, newImages).foreach{case (panel, imageOpt) =>
          panel.reset(imageOpt.map(_.f))
        }

        grid.repaint()
        println(s"${component.text} was pressed. updated ${newImages.size} photos.")
      }
      // selects a different image
      case MouseClicked(component: ImagePanel, _, _, _, _)
        if imagePanels.contains(component) && component.imagePath.isDefined => {
        println(s"panel ${component.imagePath} was clicked")
        val image = state.store(component.imagePath.get)
        // set the mainImage
        grid.mainImage.reset(component.imagePath)
        // set the tags
        buttons.zip(image.annotations).foreach{case (button, tag) => button.text = tag.getName}
        // reset the panels
        imagePanels.foreach(_.reset(None))
        grid.repaint()
      }
    }
  }
}




