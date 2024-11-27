package com.example.episafezone

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.adapter.CrisisAdapter
import com.example.episafezone.businesslogic.CalendarLogic
import com.example.episafezone.databinding.ActivityCalendarBinding
import com.example.episafezone.models.Crisis
import com.example.episafezone.models.Patient
import com.example.episafezone.network.CalendarPetitions
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class ActivityCalendar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val patient = intent.getSerializableExtra("patient") as Patient
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contextObj = this
        CalendarPetitions.initializeQueue()
        val dateTime = LocalDateTime.now()
        val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val actualDate = dateTime.format(format)
        calendarDay = CalendarDay.from(dateTime.year,dateTime.monthValue,dateTime.dayOfMonth)
        binding.selectedDayText.text = actualDate
        CalendarLogic.getCrisisList(patient, dateTime.monthValue + 1, dateTime.year)

        binding.profileButt.setOnClickListener() {
            finish()
        }
        binding.calendarView.setOnMonthChangedListener(){widget, date->
            CalendarLogic.getCrisisList(patient, date.month + 2, dateTime.year)

        }
        binding.calendarView.setOnDateChangedListener{ widget, date, selected ->
            binding.selectedDayText.text = "${date.day}/${date.month+1}/${date.year}"
            CalendarLogic.showCrisis(binding,date,list)
        }
    }

    companion object{
        private lateinit var contextObj: Context
        private lateinit var binding : ActivityCalendarBinding
        private lateinit var calendarDay : CalendarDay
        private lateinit var list : MutableList<Crisis>

        fun getContext() : Context {
            return contextObj
        }

        fun updateListOfCrisis(list: MutableList<Crisis>){
            binding.crisisRecycler.adapter = CrisisAdapter(contextObj,list);
            binding.crisisRecycler.layoutManager = LinearLayoutManager(contextObj);
        }

        fun initiateCalendar(list: MutableList<Crisis>){
            this.list = list
            CalendarLogic.showCrisis(binding,calendarDay,list)
            CalendarLogic.setUpCalendar(binding, list)
        }
    }
}