package com.example.episafezone.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.MainActivity
import com.example.episafezone.R
import com.example.episafezone.adapter.CrisisAdapter
import com.example.episafezone.businesslogic.CalendarLogic
import com.example.episafezone.databinding.FragmentCalendarBinding
import com.example.episafezone.models.Crisis
import com.example.episafezone.models.Patient
import com.example.episafezone.network.CalendarPetitions
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CalendarPetitions.initializeQueue()
        val dateTime = LocalDateTime.now()
        val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val actualDate = dateTime.format(format)
        calendarDay = CalendarDay.from(dateTime.year,dateTime.monthValue,dateTime.dayOfMonth)
        binding.dateText.text = actualDate
        CalendarLogic.getCrisisList(patient, dateTime.monthValue + 1, dateTime.year)
        binding.calendarView.visibility = View.VISIBLE
        binding.fragmentState.visibility = View.GONE

        setCalendarWeekNames()
        binding.calendarView.setOnMonthChangedListener(){widget, date->
            CalendarLogic.getCrisisList(patient, date.month + 2, dateTime.year)

        }
        binding.calendarView.setOnDateChangedListener{ widget, date, selected ->
            binding.dateText.text = "${date.day}/${date.month+1}/${date.year}"
            CalendarLogic.showCrisis(binding,date,list)
        }
        binding.showDetailsButt.setOnClickListener{
            if(binding.crisisRecycler.visibility == View.GONE){
                binding.crisisRecycler.visibility = View.VISIBLE
                binding.showLayout.background = ContextCompat.getDrawable(this.requireContext(),R.drawable.upper_rounded_box)
                binding.calendarView.visibility = View.GONE
                binding.fragmentState.visibility = View.VISIBLE
            }else{
                binding.crisisRecycler.visibility = View.GONE
                binding.showLayout.background = ContextCompat.getDrawable(this.requireContext(),R.drawable.rounded_box)

            }
        }

        val gestureDetectorCalendar = GestureDetector(this.getContext(), object : GestureDetector.SimpleOnGestureListener() {

            override fun onFling(
                e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float
            ): Boolean {
                if (e1 != null && e2 != null) {
                    if (e2.y - e1.y > 100 && Math.abs(velocityY) > 10) {
                        binding.calendarView.visibility = View.GONE
                        binding.fragmentState.visibility = View.VISIBLE
                        println("pabajo")
                    } else if(e1.y - e2.y > 100 && Math.abs(velocityY) > 10){
                        binding.calendarView.visibility = View.VISIBLE
                        binding.calendarView.visibility = View.GONE
                        println("parriba")
                    }
                }
                return super.onFling(e1, e2, velocityX, velocityY)
            }
        })

        val gestureDetectorFragments = GestureDetector(this.getContext(), object : GestureDetector.SimpleOnGestureListener() {

            override fun onFling(
                e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float
            ): Boolean {
                if(e1!!.x - e2.x > 100 && Math.abs(velocityX) > 10){
                        MainActivity.changeToChart()
                        println("derecha")
                }
                return super.onFling(e1, e2, velocityX, velocityY)
            }
        })

        binding.calendarView.setOnTouchListener{ v: View?, event: MotionEvent ->
            gestureDetectorCalendar.onTouchEvent(event)
        }
        binding.mainCalendar.setOnTouchListener{ v: View?, event: MotionEvent ->
            gestureDetectorFragments.onTouchEvent(event)
        }
        binding.mainCalendar.setOnClickListener{
            print("tocado")
        }

        binding.helperLayout.setOnClickListener{
            binding.calendarView.visibility = View.VISIBLE
            binding.fragmentState.visibility = View.GONE
            binding.crisisRecycler.visibility = View.GONE
            binding.showLayout.background = ContextCompat.getDrawable(this.requireContext(),R.drawable.rounded_box)

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

    private fun setCalendarWeekNames(){
        binding.calendarView.setWeekDayFormatter(WeekDayFormatter { dayOfWeek ->
            when (dayOfWeek) {
                Calendar.MONDAY -> "L"
                Calendar.TUESDAY -> "M"
                Calendar.WEDNESDAY -> "X"
                Calendar.THURSDAY -> "J"
                Calendar.FRIDAY -> "V"
                Calendar.SATURDAY -> "S"
                Calendar.SUNDAY -> "D"
                else -> ""
            }
        })
    }
}