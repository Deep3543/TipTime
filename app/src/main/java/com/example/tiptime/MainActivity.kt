package com.example.tiptime

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        binding.calculateButton.setOnClickListener { calculateTip() }

    }

//    Function to calculate the tip
    fun calculateTip(){
//        Calculate the tip
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()

//        Depends on the selected Radio button determine the percentage
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

//        Calculate the tip
        var tip = tipPercentage * cost

//        Round up the tip if required
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }

//        Format the tip
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

//        Assign the tip result
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}