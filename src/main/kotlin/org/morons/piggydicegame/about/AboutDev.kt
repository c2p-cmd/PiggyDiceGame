package org.morons.piggydicegame.about

import javafx.animation.*
import javafx.event.*
import javafx.geometry.*
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.stage.*
import javafx.util.Duration
import org.morons.piggydicegame.PigGameApplication
import kotlin.math.pow

const val DEV_NAME = "Sharan Thakur"

//Function to set the in ExpIn interpolator
private val expIn: Interpolator = object : Interpolator() {
    override fun curve(timeDuration: Double): Double {
        return if (timeDuration == 1.0) 1.0
            else 1 - 2.0.pow(-10 * timeDuration)
    }
}

//Function to set the ExpOut interpolator
private val expOut: Interpolator = object : Interpolator() {
    override fun curve(timeDuration: Double): Double {
        return if (timeDuration == 0.0) 0.0
            else 2.0.pow(10 * (timeDuration - 1))
    }
}

class AboutDev :Runnable {
    override fun run() {
        val dialog = CustomDialog()
        dialog.openDialog()
    }

    private inner class CustomDialog : Stage() {
        // private data members
        private val scale1 = ScaleTransition()
        private val scale2 = ScaleTransition()
        private val anim = SequentialTransition(scale1, scale2)

        fun openDialog() {
            show()
            isResizable=true
            isAlwaysOnTop=true
            anim.play()
        }

        fun closeDialog() {
            anim.onFinished = EventHandler { close() }
            anim.isAutoReverse = true
            anim.cycleCount = 2
            anim.playFrom(Duration.seconds(0.66))
        }

        // Constructor
        init {
            val aboutRootPane = Pane()
            scale1.fromX = 0.01
            scale1.fromY = 0.01
            scale1.toY = 1.0
            scale1.duration = Duration.seconds(0.2)
            scale1.interpolator = expIn
            scale1.node = aboutRootPane
            scale2.fromX = 0.01
            scale2.toX = 1.0
            scale2.duration = Duration.seconds(0.2)
            scale2.interpolator = expOut
            scale2.node = aboutRootPane
            val space = Label()

            space.prefWidth = 120.0
            initStyle(StageStyle.TRANSPARENT) //Set the scene transparent

            // Label to store header text of about Us
            val aboutHeaderText = Label("About The Dev:\n$DEV_NAME\n\n\n")
            aboutHeaderText.id = "headerLabel"


            // Label to store content text
            val aboutContentText = Label(
                "This is the Pig Dice Game between two players\n" +
                        "just for fun\n" +
                        "Hope you all enjoy it!"
            )
            aboutContentText.id = "contentLabel"

            // Creating image view and label for it
            val aboutDevImageLabel = Label()
            aboutDevImageLabel.graphic =  ImageView(
                Image(
                    PigGameApplication::class.java.getResourceAsStream("PigImage/aboutDev.png")
                )
            )
            aboutDevImageLabel.prefHeight = 50.0
            aboutDevImageLabel.prefWidth = 50.0
            aboutDevImageLabel.id = "aboutDevImageLabel"

            // HBox to store header and image label
            val aboutDevHeaderImage = HBox(5.0, aboutHeaderText, space, aboutDevImageLabel)

            // VBox to store the labels
            val aboutUsVbox =
                VBox(11.0, aboutDevHeaderImage,  Separator(Orientation.HORIZONTAL), aboutContentText)

            // CSS for the Vbox
            aboutUsVbox.id = "dialogueVbox"
            aboutUsVbox.padding = Insets(10.0, 0.0, 0.0, 15.0)
            aboutUsVbox.prefHeight = 270.0
            aboutUsVbox.prefWidth = 350.0


            // Button to close the dialogue and its CSS
            val aboutDevOkButton = Button("OK")

            aboutDevOkButton.prefHeight = 26.0
            aboutDevOkButton.prefWidth = 55.0
            aboutDevOkButton.id = "dialogueOkButton"
            aboutDevOkButton.translateX = 285.0
            aboutDevOkButton.translateY = 233.0
            aboutDevOkButton.setOnAction { closeDialog() }

            // Adding all the nodes to the root pane
            aboutRootPane.children.addAll(aboutUsVbox, aboutDevOkButton)

            // Creating a scene
            val scene = Scene(aboutRootPane, aboutUsVbox.prefWidth, aboutUsVbox.prefHeight)
            setScene(scene)
        }
    }
}