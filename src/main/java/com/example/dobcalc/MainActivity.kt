package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView ? = null
    private var tvAgeInMinutes : TextView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate =findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes =findViewById((R.id.ageinminutes))
        btnDatePicker.setOnClickListener{
            clickbtnDatePicker()
        }
    }
    fun clickbtnDatePicker()
    {   val myCalendar= Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day= myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view,year,month,dayOfMonth->
                Toast.makeText(this,
                    "DatePicker works",Toast.LENGTH_LONG).show()
                val selecteddate="$dayOfMonth/${month+1}/$year"
                tvSelectedDate?.setText(selecteddate)
                val sdf =SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selecteddate)
                val selecteddateinminutes=theDate.time/60000
                val currentdate=sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentdateinminutes =currentdate.time/60000
                val difference = currentdateinminutes-selecteddateinminutes
                tvAgeInMinutes?.text=difference.toString()

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()

    }
}