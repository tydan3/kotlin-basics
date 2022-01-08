package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDice() }

        // Do a dice roll on app start
//        rollDice()
    }

    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice() {
        val diceImage1: ImageView = findViewById(R.id.imageView)
        val diceImage2: ImageView = findViewById(R.id.imageView2)

        // Create new Dice object with 6 sides and roll it
        val dice1 = Dice(6)
        val diceRoll1 = dice1.roll()

        // Second dice
        val dice2 = Dice(6)
        val diceRoll2 = dice2.roll()

        // Determine which dice drawable resource to use based on dice roll
        val drawableResource1 = when (diceRoll1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        val drawableResource2 = when (diceRoll2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // Update image resource with correct dice drawable
        diceImage1.setImageResource(drawableResource1)
        diceImage2.setImageResource(drawableResource2)

        // Update content description with correct dice number
        diceImage1.contentDescription = (diceRoll1.toString())
        diceImage2.contentDescription = (diceRoll2.toString())

        // Update textView
        val resultText: TextView = findViewById(R.id.textView)
        val result = diceRoll1 + diceRoll2
        resultText.text = getString(R.string.dice_roll_result, result)
        resultText.contentDescription = result.toString()
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}

//class Coin() {
//    fun flip(): String {
//        return if ((1..2).random() % 2 == 0) "heads" else "tails"
//    }
//}