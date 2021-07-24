package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

//    Function to calculate the tip
    private fun calculateTip(){
//        Calculate the tip
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

//        Depends on the selected Radio button determine the percentage
    val tipPercentage = when(binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
//        Calculate the tip
        var tip = tipPercentage * cost!!
//        Round up the tip if required
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp){
            tip = ceil(tip)
        }

//        Format the tip
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

//        Assign the tip result
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}