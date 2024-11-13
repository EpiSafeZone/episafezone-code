package com.example.episafezone.businesslogic

import android.graphics.Color
import com.example.episafezone.ActivityCalendar
import com.example.episafezone.databinding.ActivityCalendarBinding
import com.example.episafezone.decorator.DayDecoratorRed
import com.example.episafezone.decorator.DayDecoratorYellow
import com.example.episafezone.models.Crisis
import com.example.episafezone.models.Patient
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Date

object CalendarLogic {

    fun getCrisisList(patient : Patient, month : Int, year : Int) : MutableList<Crisis>{
        val crisisList = mutableListOf<Crisis>()
        var date = Date()
        date.date=10
        date.month=11
        date.year=2024

        val date2 = Date()
        date2.date=8
        date2.month=11
        date2.year=2024

        val cri = Crisis(1,5,date,"23:00","Jugando a la play",false,"Convulsión",1)
        val cri2 = Crisis(2,5,date,"12:15","En clase",false,"Tic",1)
        val cri3 = Crisis(2,5,date2,"12:15","En clase",false,"Tic",1)

        crisisList.add(cri)
        crisisList.add(cri2)
        crisisList.add(cri3)

        return crisisList
    }

    fun setUpCalendar(binding : ActivityCalendarBinding, list:List<Crisis>){
        val colorMapYellow = mutableMapOf<CalendarDay, Int>()
        val colorMapRed = mutableMapOf<CalendarDay, Int>()
        for(i in 1..32){
            val crisis = list.filter{it.date.date == i}
            if(crisis.count()>0){
                val date = crisis.get(0).date
                println(crisis.count())
                if(crisis.count()==1){
                    colorMapYellow[CalendarDay.from(date.year,date.month-1,date.date)] = Color.parseColor("#FFFF00")
                }else{
                    colorMapRed[CalendarDay.from(date.year,date.month-1,date.date)] = Color.parseColor("#FE2020")
                }
            }
        }
        val decorator = DayDecoratorRed(colorMapRed)
        val decorator2 = DayDecoratorYellow(colorMapYellow)
        binding.calendarView.addDecorators(decorator,decorator2)
    }

    fun showCrisis(binding : ActivityCalendarBinding, calendarDate : CalendarDay, list : MutableList<Crisis>){
        val result = mutableListOf<Crisis>()
        for(crisis in list){
            if(crisis.date.date == calendarDate.day){
                result.add(crisis)
            }
        }

        binding.amountCrisisNumber.text = result.count().toString()
        ActivityCalendar.updateListOfCrisis(result)

    }

}