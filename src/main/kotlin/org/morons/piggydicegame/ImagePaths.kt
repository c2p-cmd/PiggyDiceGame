package org.morons.piggydicegame

import javafx.scene.image.Image

val diceImages: List<Image> =
    listOf(
        Image(
            PigGameApplication::class.java.getResourceAsStream(
                "DiceImages/1.png"
            )
        ),
        Image(
            PigGameApplication::class.java.getResourceAsStream(
                "DiceImages/2.png"
            )
        ),
        Image(
            PigGameApplication::class.java.getResourceAsStream(
                "DiceImages/3.png"
            )
        ),
        Image(
            PigGameApplication::class.java.getResourceAsStream(
                "DiceImages/4.png"
            )
        ),
        Image(
            PigGameApplication::class.java.getResourceAsStream(
                "DiceImages/5.png"
            )
        ),
        Image(
            PigGameApplication::class.java.getResourceAsStream(
                "DiceImages/6.png"
            )
        )
    )

val pigTitleImage: Image = Image(
    PigGameApplication::class.java.getResourceAsStream(
        "PigImage/0.png"
    )
)