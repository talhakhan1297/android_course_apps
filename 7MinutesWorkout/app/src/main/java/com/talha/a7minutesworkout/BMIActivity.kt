package com.talha.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.talha.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"
    }

    private var binding: ActivityBmiBinding? = null
    private var currentVisibleView: String = METRIC_UNITS_VIEW


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBMIActivity)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }

        binding?.toolbarBMIActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        showMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener {_, checkedID: Int ->
            if (checkedID == R.id.rbMetric) {
                showMetricUnitsView()
            } else {
                showUSUnitsView()
            }
        }

        binding?.btnCalculateUnits?.setOnClickListener {
            calculateUSUnits()
        }
    }

    private fun showMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tilMetricHeight?.visibility = View.VISIBLE
        binding?.tilMetricWeight?.visibility = View.VISIBLE
        binding?.tilUSWeight?.visibility = View.INVISIBLE
        binding?.tilUSHeightFeet?.visibility = View.INVISIBLE
        binding?.tilUSHeightInches?.visibility = View.INVISIBLE

        binding?.etMetricHeight?.text!!.clear()
        binding?.etMetricWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun showUSUnitsView() {
        currentVisibleView = US_UNITS_VIEW
        binding?.tilMetricHeight?.visibility = View.INVISIBLE
        binding?.tilMetricWeight?.visibility = View.INVISIBLE
        binding?.tilUSWeight?.visibility = View.VISIBLE
        binding?.tilUSHeightFeet?.visibility = View.VISIBLE
        binding?.tilUSHeightInches?.visibility = View.VISIBLE

        binding?.etUSWeight?.text!!.clear()
        binding?.etUSHeightFeet?.text!!.clear()
        binding?.etUSHeightInches?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun displayBMI(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.tvBMIValue?.text = bmiValue // Value is set to TextView
        binding?.tvBMIType?.text = bmiLabel // Label is set to TextView
        binding?.tvBMIDescription?.text = bmiDescription // Description is set to TextView
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (binding?.etMetricWeight?.text.toString().isEmpty()) {
            isValid = false
        } else if (binding?.etMetricHeight?.text.toString().isEmpty()) {
            isValid = false
        }

        return isValid
    }

    private fun calculateUSUnits() {
        if (currentVisibleView == METRIC_UNITS_VIEW) {
            if (validateMetricUnits()) {
                val height : Float = binding?.etMetricHeight?.text.toString().toFloat() / 100

                val weight : Float = binding?.etMetricWeight?.text.toString().toFloat()

                val bmi = weight / (height * height)

                displayBMI(bmi)
            } else {
                Toast.makeText(
                    this,
                    "Please enter valid values.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        } else {
            if (validateUSUnits()) {
                val heightFt : Float = binding?.etUSHeightFeet?.text.toString().toFloat()
                val heightIn : Float = binding?.etUSHeightInches?.text.toString().toFloat()

                val height = heightIn + heightFt * 12
                val weight : Float = binding?.etUSWeight?.text.toString().toFloat()

                val bmi = 703 * (weight / (height * height))

                displayBMI(bmi)
            } else {
                Toast.makeText(
                    this,
                    "Please enter valid values.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
    }

    private fun validateUSUnits(): Boolean {
        var isValid = true

        when {
            binding?.etUSWeight?.text.toString().isEmpty() -> {
                isValid = false
            }
            binding?.etUSHeightFeet?.text.toString().isEmpty() -> {
                isValid = false
            }
            binding?.etUSHeightInches?.text.toString().isEmpty() -> {
                isValid = false
            }
        }

        return isValid
    }
}