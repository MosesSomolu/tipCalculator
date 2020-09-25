package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

private lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        calculate()

    }

    //METHODS

    private fun calculate(){
        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val cost = binding.costOfService.text.toString().toDoubleOrNull()

        //set the text view to an empty string
        //if the text view is null exit the method without executing the rest of the code
        if (cost == null || cost == 0.0 ){
            displayTip(0.0)
            return
        }
        //check tip percentage
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.amazing -> 0.20
            R.id.good -> 0.18
            else -> 0.15
        }
        //calculate tip based on the tip percentage checked on the radio buttons
        var tip = tipPercentage * cost
        if (binding.roundTip.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        // format the tip and
        //display the formatted tip in the textView(tipResult)
        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }


}