package com.example.episafezone.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.MainActivity
import com.example.episafezone.R
import com.example.episafezone.adapter.CrisisAdapter
import com.example.episafezone.businesslogic.CalendarLogic
import com.example.episafezone.databinding.FragmentCalendarBinding
import com.example.episafezone.databinding.ActivityCalendarBinding
import com.example.episafezone.fragments.ChartFragment
import com.example.episafezone.models.Crisis
import com.example.episafezone.models.Patient
import com.example.episafezone.network.CalendarPetitions
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalendarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CalendarPetitions.initializeQueue()
        val dateTime = LocalDateTime.now()
        val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val actualDate = dateTime.format(format)
        calendarDay = CalendarDay.from(dateTime.year,dateTime.monthValue,dateTime.dayOfMonth)
        binding.selectedDayText.text = actualDate
        CalendarLogic.getCrisisList(patient, dateTime.monthValue + 1, dateTime.year)

        binding.calendarView.setOnMonthChangedListener(){widget, date->
            CalendarLogic.getCrisisList(patient, date.month + 2, dateTime.year)

        }
        binding.calendarView.setOnDateChangedListener{ widget, date, selected ->
            binding.selectedDayText.text = "${date.day}/${date.month+1}/${date.year}"
            CalendarLogic.showCrisis(binding,date,list)
        }

        binding.initChart.setOnClickListener(){
            val list = mutableListOf<String>();
            list.add("hola")
            list.add("tardes")
            list.add("tardes")
            list.add("hola")
            list.add("buenas")
            list.add("hola")

            val fragment = ChartFragment(patient,list)
            supportFragmentManager.beginTransaction()
                .replace(R.id.calendar_layout, fragment)
                .commit()
        }
    }

    companion object {
        private lateinit var binding : FragmentCalendarBinding
        private lateinit var calendarDay : CalendarDay
        private lateinit var list : MutableList<Crisis>

        private var patient = MainActivity.getPatient()
        private val contextObj = MainActivity.getContext()

        fun getContext() : Context {
            return contextObj
        }

        fun updateListOfCrisis(list: MutableList<Crisis>){
            binding.crisisRecycler.adapter = CrisisAdapter(contextObj,list);
            binding.crisisRecycler.layoutManager = LinearLayoutManager(contextObj);
        }

        fun initiateCalendar(list: MutableList<Crisis>){
            this.list = list
            CalendarLogic.showCrisis(binding, calendarDay,list)
            CalendarLogic.setUpCalendar(binding, list)
        }

        fun updatePatient(patient: Patient){
            this.patient = patient
        }
    }
}