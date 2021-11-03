package org.morons.piggydicegame

import javafx.animation.AnimationTimer
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable

import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import org.morons.piggydicegame.about.AboutDev

import org.morons.piggydicegame.about.AboutGame

import org.morons.piggydicegame.model.*

import java.net.URL
import java.util.*

const val FRAMES_PER_SEC = 24L
const val INTERVAL = 1000000000L / FRAMES_PER_SEC
const val MAX_ROLLS = 12
const val FOCUS_COLOUR = "-fx-background-color:\n" +
                            "rgba(0,0,0,0.08),\n" +
                            "linear-gradient(#5a61af, #51536d),\n" +
                            "linear-gradient(#e4fbff 0%,#cee6fb 10%, #a5d3fb 50%, #88c6fb 51%, #d5faff 100%);\n" +
                         "-fx-border-color: \n" +
                            "rgb(212,212,221);\n"

class PigGameController : Initializable {

    // data fields
    private lateinit var game: Game
    private lateinit var clock: Roller

    // MenuItems
    @FXML
    lateinit var newGameMenuItem: MenuItem

    @FXML
    lateinit var quitMenuItem: MenuItem

    @FXML
    lateinit var aboutGameMenuItem: MenuItem

    @FXML
    lateinit var aboutTheDevMenuItem: MenuItem

    // Label
    @FXML
    lateinit var titleLabel: Label

    // ImageView
    @FXML
    lateinit var pigImageView: ImageView

    @FXML
    lateinit var dieImageView: ImageView

    // TextFields
    @FXML
    lateinit var player1TurnField: TextField

    @FXML
    lateinit var player1ScoreField: TextField

    @FXML
    lateinit var player2TurnField: TextField

    @FXML
    lateinit var player2ScoreField: TextField

    // Buttons
    @FXML
    lateinit var rollBtn: Button

    @FXML
    lateinit var holdBtn: Button

    // VBox
    @FXML
    lateinit var p1VBox: VBox

    @FXML
    lateinit var p2VBox: VBox

    @FXML
    lateinit var rootVBox: VBox

    override fun initialize(url: URL?, resourceBundle: ResourceBundle?) {
        // setting pig image view
        pigImageView.image = pigTitleImage

        // resetting everything
        resetFields()

        // init clock
        clock = Roller()

        // adding functionality to menu item
        newGameMenuItem.setOnAction { resetFields() }
        quitMenuItem.setOnAction { PigGameApplication.quitAction() }

        aboutGameMenuItem.setOnAction { Platform.runLater(AboutGame()) }
        aboutTheDevMenuItem.setOnAction { Platform.runLater(AboutDev()) }
        //

        // starting Game
        game = Game("Player1", "Player 2")

        rollBtn.setOnAction { rollAnimation() }
        holdBtn.setOnAction { holdAction() }
    }

    private fun updateViews() {
        setDieImage(game.die.top)
        if (game.isP1Turn){
            p1VBox.style = FOCUS_COLOUR
            p2VBox.style = null
        } else {
            p2VBox.style= FOCUS_COLOUR
            p1VBox.style = null
        }

        val (p1Scores, p2Scores) = game.playerScores

        player1TurnField.text = p1Scores.first.toString()
        player1ScoreField.text = p1Scores.second.toString()

        player2TurnField.text = p2Scores.first.toString()
        player2ScoreField.text = p2Scores.second.toString()

        if (game.isGameOver) {
            disableButtons(true)
            titleLabel.text = "Game Over! ${game.currentPlayer.name} wins!"
        }
    }

    private inner  class Roller : AnimationTimer(){
        private var last =0L
        private var count =0L

        override fun handle(now: Long) {
            if(now-last> INTERVAL){
                setDieImage(
                    (1..6).random()
                )
                last=now
                count++

                if(count> MAX_ROLLS){
                    clock.stop()
                    disableButtons(false)
                    rollAction()
                    count=0L
                }
            }
        }

    }

    private fun rollAnimation() {
        clock.start()
        disableButtons(true)
    }

    private fun setDieImage(top: Int) {
        if (top == 0) dieImageView.image = pigFaceImage
        else dieImageView.image = diceImages[top-1]
    }

    private fun rollAction() {
        game.roll()
        updateViews()
    }

    private fun holdAction() {
        game.hold()
        updateViews()
    }

    private fun disableButtons(disable: Boolean) {
        rollBtn.isDisable = disable
        holdBtn.isDisable = disable
    }

    private fun resetFields() {
        player1TurnField.text = "0"
        player1ScoreField.text = "0"

        player2TurnField.text = "0"
        player2ScoreField.text = "0"

        disableButtons(false)
        setDieImage(0)

        p1VBox.style = null
        p2VBox.style = null
    }
}