package org.morons.piggydicegame.model

const val MAX_SIDES = 6;

class Die(top: Int = 0) {
    // data field
    var top: Int = top
    set(value) {
        if (value in 1..MAX_SIDES)
            field = value
    }

    // Game Play Methods
    fun rollDie() {
        this.top = (1..6).random()
    }
}