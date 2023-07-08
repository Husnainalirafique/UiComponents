package com.example.uicomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
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
        listners()
    }

    private fun listners() {

        binding.timeDialog.setOnClickListener {
            showMaterialTimePicker()
        }
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
        //AutoComplete TextView
        val suggestions = arrayOf("Apple", "Banana", "Cherry", "Date", "Elderberry")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, suggestions)
        binding.autoCompleteTv.setAdapter(adapter)

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
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
            }

        }

        //Switch
//        binding.switchDarkLight.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked){
//                binding.mainActivityBg.setBackgroundColor(resources.getColor(R.color.black,null))
//                binding.switchDarkLight.setTextColor(resources.getColor(R.color.white,null))
//            }
//            else{
//                binding.mainActivityBg.setBackgroundColor(resources.getColor(R.color.white,null))
//                binding.switchDarkLight.setTextColor(resources.getColor(R.color.black,null))
//            }
//        }

    }

    private fun showMaterialDatePicker(){
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setInputMode(INPUT_MODE_TEXT)
            .setTitleText("Select Date of Birth")
            .build()

        datePicker.show(supportFragmentManager,"tag")
    }

    private fun showMaterialTimePicker() {
        val isSystem24Hour = is24HourFormat(this)
        val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val picker = MaterialTimePicker.Builder()
            .setInputMode(INPUT_MODE_KEYBOARD)
            .setTimeFormat(clockFormat)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Time")
            .build()

        picker.show(supportFragmentManager, "tag")
        picker.addOnPositiveButtonClickListener {
            val h = picker.hour
            val m = picker.minute
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