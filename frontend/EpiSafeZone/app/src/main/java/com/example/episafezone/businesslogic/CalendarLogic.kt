package com.example.episafezone.businesslogic

import android.graphics.Color
import com.example.episafezone.fragments.CalendarFragment
import com.example.episafezone.databinding.FragmentCalendarBinding
import com.example.episafezone.decorator.DayDecoratorRed
import com.example.episafezone.decorator.DayDecoratorYellow
import com.example.episafezone.models.Crisis
import com.example.episafezone.models.Patient
import com.example.episafezone.network.CalendarPetitions
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.prolificinteractive.materialcalendarview.CalendarDay
import org.json.JSONArray
import org.json.JSONObject

object CalendarLogic {

    val gson = Gson()

    fun getCrisisList(patient : Patient, month : Int, year : Int){
        CalendarPetitions.getMonthCrisis(month,year,patient)
    }

    fun setUpCalendar(binding : FragmentCalendarBinding, list:List<Crisis>){
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
        binding.calendarView.removeDecorators()
        binding.calendarView.invalidateDecorators()
        val decorator = DayDecoratorRed(colorMapRed)
        val decorator2 = DayDecoratorYellow(colorMapYellow)
        binding.calendarView.addDecorators(decorator,decorator2)
    }

    fun showCrisis(binding : FragmentCalendarBinding, calendarDate : CalendarDay, list : MutableList<Crisis>){
        val result = mutableListOf<Crisis>()
        for(crisis in list){
            if(crisis.date.date == calendarDate.day){
                result.add(crisis)
            }
        }

        binding.crisisAmountText.text = result.count().toString() +" crisis registradas";
        CalendarFragment.updateListOfCrisis(result)
    }
    fun prepareCalendarInitiation(json : String){
        val gson = Gson()
        val jsonObject = gson.fromJson(json, JsonObject::class.java)
        val jsonArray = jsonObject.getAsJsonArray("crisis")
        for(crisis in jsonArray){
            val manif = crisis.asJsonObject.get("manifestation").asJsonObject
            crisis.asJsonObject.addProperty("manifestation",manif.get("name").asString)
        }
        val listType = object : TypeToken<MutableList<Crisis>>() {}.type
        val crisis : MutableList<Crisis> = gson.fromJson(jsonArray.toString(),listType)
        CalendarFragment.initiateCalendar(crisis)
    }

}