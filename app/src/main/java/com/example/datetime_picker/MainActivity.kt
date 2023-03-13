package com.example.datetime_picker

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.datetime_picker.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val calenderDate = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayofmonth ->
            calenderDate.set(Calendar.YEAR, year)
            calenderDate.set(Calendar.MONTH,month)
            calenderDate.set(Calendar.DAY_OF_MONTH, dayofmonth)
            updateDateLevel(calenderDate)
        }

        val calenderTime = Calendar.getInstance()
        val timePicker = TimePickerDialog.OnTimeSetListener { timePicker, hourofday, minutesofday ->
            calenderTime.set(Calendar.HOUR_OF_DAY, hourofday)
            calenderTime.set(Calendar.MINUTE, minutesofday)
            calenderTime.timeZone = TimeZone.getDefault()
            updateTimeLevel(calenderTime)
        }

        binding.selectdate.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker, calenderDate.get(Calendar.YEAR),
                calenderDate.get(Calendar.MONTH),
                calenderDate.get(Calendar.DAY_OF_MONTH)
            ).show()


        }
        binding.Selecttime.setOnClickListener {
            TimePickerDialog(
                this,
                timePicker, calenderTime.get(Calendar.HOUR_OF_DAY),
                calenderTime.get(Calendar.MINUTE),
                false
            ).show()

        }


    }

    @SuppressLint("SimpleDateFormat")
    private fun updateTimeLevel(calenderTime: Calendar) {
        val format=SimpleDateFormat("hh::mm::aa")
        val time=format.format(calenderTime.time)
        binding.time.text= time

    }
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun updateDateLevel(calenderDate: Calendar){
        val day=SimpleDateFormat("dd").format(calenderDate.time)
        val month=SimpleDateFormat("MM").format(calenderDate.time)
        val year=SimpleDateFormat("yyyy").format(calenderDate.time)
        binding.Date.text="${day}/${month}/${year}"
    }
}

