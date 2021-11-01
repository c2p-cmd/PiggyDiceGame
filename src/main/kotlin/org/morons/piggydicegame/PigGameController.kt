package org.morons.piggydicegame

import javafx.fxml.FXML
import javafx.fxml.Initializable

import javafx.scene.control.*
import javafx.scene.image.ImageView

import java.net.URL
import java.util.*

class PigGameController : Initializable {
    @FXML
    lateinit var newGameMenuItem: MenuItem

    @FXML
    lateinit var quitMenuItem: MenuItem

    @FXML
    lateinit var aboutGameMenuItem: MenuItem

    @FXML
    lateinit var aboutUsMenuItem: MenuItem

    @FXML
    lateinit var pigImageView: ImageView

    @FXML
    lateinit var player1TurnField: TextField

    @FXML
    lateinit var player1ScoreField: TextField

    @FXML
    lateinit var dieImageView: ImageView

    @FXML
    lateinit var player2TurnField: TextField

    @FXML
    lateinit var player2ScoreField: TextField

    @FXML
    lateinit var rollBtn: Button

    @FXML
    lateinit var holdBtn: Button

    override fun initialize(url: URL?, resourceBundle: ResourceBundle?) {
        dieImageView.image = diceImages.random()
    }
}