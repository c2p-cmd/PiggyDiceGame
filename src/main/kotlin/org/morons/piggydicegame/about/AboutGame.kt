package org.morons.piggydicegame.about

import javafx.animation.Interpolator
import javafx.animation.ScaleTransition
import javafx.animation.SequentialTransition

import javafx.event.EventHandler

import javafx.geometry.Insets
import javafx.geometry.Orientation

import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.*
import javafx.scene.layout.*

import javafx.stage.*

import javafx.util.Duration
import org.morons.piggydicegame.PigGameApplication
import org.morons.piggydicegame.pigFaceImage
import org.morons.piggydicegame.pigTitleImage


import kotlin.math.pow

//Function to set the in ExpIn interpolator
private val EXP_IN: Interpolator = object : Interpolator() {
    override fun curve(t: Double): Double {
        return if (t == 1.0) 1.0 else 1 - 2.0.pow(-10 * t)
    }
}

//Function to set the ExpOut interpolator
private val EXP_OUT: Interpolator = object : Interpolator() {
    override fun curve(t: Double): Double {
        return if (t == 0.0) 0.0 else 2.0.pow(10 * (t - 1))
    }
}


class AboutGame : Runnable{
    override fun run() {
        val dialog = CustomDialog()
        dialog.openDialog()
    }


    private inner class CustomDialog : Stage() {
        private val scale1 = ScaleTransition()
        private val scale2 = ScaleTransition()
        private val anim = SequentialTransition(scale1, scale2)
        fun openDialog() {
            isResizable=true
            isAlwaysOnTop=true
            show()
            anim.play()
        }

        fun closeDialog() {
            anim.onFinished = EventHandler { close() }
            anim.isAutoReverse = true
            anim.cycleCount = 2
            anim.playFrom(Duration.seconds(0.66))
        }

        //Constructor
        init {
            val aboutGameRootPane = Pane()
            scale1.fromX = 0.01
            scale1.fromY = 0.01
            scale1.toY = 1.0
            scale1.duration = Duration.seconds(0.2)
            scale1.interpolator = EXP_IN
            scale1.node = aboutGameRootPane
            scale2.fromX = 0.01
            scale2.toX = 1.0
            scale2.duration = Duration.seconds(0.2)
            scale2.interpolator = EXP_OUT
            scale2.node = aboutGameRootPane
            val space1 = Label()
            space1.prefHeight = 10.0
            space1.prefWidth = 25.0
            val space = Label()
            space.prefHeight = 50.0
            space.prefWidth = 80.0
            initStyle(StageStyle.TRANSPARENT) //Set the scene transparent

            //Label to store header text  of about app
            val aboutGameHeaderText = Label("About \nPiggy Dice Game:\n\n\n")
            aboutGameHeaderText.id = "headerLabel"
            aboutGameHeaderText.prefWidth(150.0)

            //Label to store content text
            val aboutGameContentText = Label(
                """Pig Dice Game is 2 player dice game 
The aim of the game is to 'roll' a die 
and 'hold' points to your score
but, beware of the number '1' as rolling 
it will set your current score to 0.
"""
            )
            aboutGameContentText.id = "contentLabel"

            // Creating image view and label for it
            val aboutAppImageLabel = Label()
            aboutAppImageLabel.graphic =  ImageView(pigTitleImage)
            aboutAppImageLabel.prefHeight = 50.0
            aboutAppImageLabel.prefWidth = 50.0
            aboutAppImageLabel.id = "aboutAppImageLabel"

            // HBox to store header and image label
            val aboutGameHeaderImage = HBox(5.0, aboutGameHeaderText, space, aboutAppImageLabel)

            // VBox to store the labels
            val aboutAppVBox =
                VBox(11.0, aboutGameHeaderImage,  Separator(Orientation.HORIZONTAL), aboutGameContentText)

            // CSS for the VBox
            aboutAppVBox.id = "dialogueVbox"
            aboutAppVBox.padding = Insets(10.0, 0.0, 0.0, 15.0)
            aboutAppVBox.prefHeight = 270.0
            aboutAppVBox.prefWidth = 350.0


            // Button to close the dialogue and its Css
            val aboutGameOkButton = Button("OK")

            aboutGameOkButton.prefHeight = 26.0
            aboutGameOkButton.prefWidth = 55.0
            aboutGameOkButton.id = "dialogueOkButton"
            aboutGameOkButton.translateX = 285.0
            aboutGameOkButton.translateY = 233.0
            aboutGameOkButton.setOnAction { closeDialog() }

            // Adding all the nodes to the root pane
            aboutGameRootPane.children.addAll(aboutAppVBox, aboutGameOkButton)

            // Creating a scene
            val scene = Scene(aboutGameRootPane, aboutAppVBox.prefWidth, aboutAppVBox.prefHeight)

            setScene(scene)
        }
    }
}