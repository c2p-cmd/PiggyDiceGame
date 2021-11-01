package org.morons.piggydicegame

import javafx.scene.image.Image

val diceImages: List<Image> =
    listOf(
        Image(
            PigGame::class.java.getResourceAsStream(
                "DiceImages/1.png"
            )
        ),
        Image(
            PigGame::class.java.getResourceAsStream(
                "DiceImages/2.png"
            )
        ),
        Image(
            PigGame::class.java.getResourceAsStream(
                "DiceImages/3.png"
            )
        ),
        Image(
            PigGame::class.java.getResourceAsStream(
                "DiceImages/4.png"
            )
        ),
        Image(
            PigGame::class.java.getResourceAsStream(
                "DiceImages/5.png"
            )
        ),
        Image(
            PigGame::class.java.getResourceAsStream(
                "DiceImages/6.png"
            )
        )
    )