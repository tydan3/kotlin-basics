package com.example.tiptime

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

//        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
//            handleKeyEvent(view, keyCode)
//        }

        binding.tipOptions.setOnCheckedChangeListener { group, _ ->
            handleChangeEvent(group)
        }
    }

    private fun handleChangeEvent(group: RadioGroup?) {
        if (group?.checkedRadioButtonId == binding.optionCustom.id) {
            binding.customTip.visibility = View.VISIBLE
            binding.iconCustom.visibility = View.VISIBLE
        } else {
            binding.customTip.visibility = View.GONE
            binding.iconCustom.visibility = View.GONE
        }
    }

    private fun calculateTip() {
        val cost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }

        var customPercent = binding.customTipEditText.text.toString().toDoubleOrNull()

        if (binding.optionCustom.isChecked) {
            if (customPercent == null || customPercent == 0.0) {
                displayTip(0.0)
                return
            } else {
                customPercent /= 100
            }
        }

        var tip = cost * when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_five_percent -> 0.25
            R.id.option_twenty_percent -> 0.20
            R.id.option_fifteen_percent -> 0.15
            else -> customPercent
        }!!

        if (binding.roundUpSwitch.isChecked) tip = kotlin.math.ceil(tip)
        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

//    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_ENTER) {
//            // Hide the keyboard
//            val inputMethodManager =
//                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//            return true
//        }
//        return false
//    }

}