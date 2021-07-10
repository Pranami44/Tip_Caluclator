package com.example.tipcaluclator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tipcaluclator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip() {
        val cost = binding.costOfService.text.toString().toDoubleOrNull()
        if(cost == null){
            binding.result.text = ""
            return
        }
        val percentage = when(binding.tipOption.checkedRadioButtonId){
            R.id.twenty_percent -> 0.20
            R.id.ten_percent -> 0.10
            else -> 0.05
        }
        var tip = cost * percentage
        if(binding.roundUpSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }
        val finalTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.result.text = getString(R.string.tip_amount, finalTip)
    }
}