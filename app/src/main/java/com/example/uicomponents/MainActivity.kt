package com.example.uicomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.uicomponents.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_TEXT
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setClickListener()
    }

    private fun setClickListener() {

        binding.customToast.setOnClickListener {
            showCustomToast(this@MainActivity,"Husnain")
        }

        //Time Picker
        binding.timeDialog.setOnClickListener {
            showMaterialTimePicker()
        }

        //Date Picker
        binding.dateDialog.setOnClickListener {
            showMaterialDatePicker()
        }

        //Progress bar
        binding.btnStart.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
        }
        binding.btnStop.setOnClickListener {
            binding.progressBar.visibility = View.INVISIBLE
        }

        //Simple Alert Dialog
        binding.simpleAlertDialog.setOnClickListener {
            showSimpleAlertDialog()
        }

        //Radio Buttons click
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb1 -> {
                    Toast.makeText(this, "1", Toast.LENGTH_SHORT).show()
                }

                R.id.rb2 -> {
                    Toast.makeText(this, "2", Toast.LENGTH_SHORT).show()
                }

                R.id.rb3 -> {
                    Toast.makeText(this, "3", Toast.LENGTH_SHORT).show()
                }

                R.id.rb4 -> {
                    Toast.makeText(this, "4", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //CheckBox Click
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "CheckBox", Toast.LENGTH_SHORT).show()
            }

        }

        //Switch
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Switch", Toast.LENGTH_SHORT).show()
            }
        }

        binding.customAlertDialog.setOnClickListener {
            CustomDialog.showDialog(this@MainActivity){
                Toast.makeText(this, "confirm", Toast.LENGTH_SHORT).show()
            }
        }
        val items = listOf("Apple","Banana","Crop","Danish")
        val adapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,items)
        binding.autoCompleteTv
    }

    private fun showSimpleAlertDialog() {
        val builder = AlertDialog.Builder(this@MainActivity)
        with(builder) {
            setMessage("Do you want to exit ?")
            setTitle("Alert !")
            setCancelable(false)
            setPositiveButton("Yes") { _, _ ->
                finish()
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
            setCancelable(false)
            create()
            show()
        }
    }

    private fun showMaterialDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setInputMode(INPUT_MODE_TEXT)
            .setTitleText("Select Date of Birth")
            .build()

        datePicker.show(supportFragmentManager, "tag")
    }

    private fun showMaterialTimePicker() {
        val isSystem24Hour = is24HourFormat(this)
        val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val timPicker = MaterialTimePicker.Builder()
            .setInputMode(INPUT_MODE_KEYBOARD)
            .setTimeFormat(clockFormat)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Time")
            .build()

        timPicker.show(supportFragmentManager, "tag")
        timPicker.addOnPositiveButtonClickListener {
            val h = timPicker.hour
            val m = timPicker.minute
            val hm = "Time is = $h:$m"
            binding.tvToSetTime.text = hm
        }
    }

    override fun onResume() {
        super.onResume()
        val languages = arrayOf("English", "Urdu", "Arabic", "Chinese")
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, languages)
        binding.autoCompleteTextView2.setAdapter(adapter2)

    }

}