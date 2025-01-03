package com.example.episafezone.network

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.BuildConfig
import com.example.episafezone.businesslogic.CalendarLogic
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.fragments.CalendarFragment
import com.example.episafezone.models.Patient
import org.json.JSONObject

object CalendarPetitions {

    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(CalendarFragment.getContext())
    }

    fun getMonthCrisis(month:Int,year:Int,patient:Patient){
            println("${url}/patient/crisis/${patient.id}/${year}/${month-1}")
            val json = JSONObject()
            val jsonRequest = JsonObjectRequest(
                Request.Method.GET, "${url}/patient/crisis/${patient.id}/${year}/${month-1}", json,
                {response->
                    println(response.toString())
                    CalendarLogic.prepareCalendarInitiation(response.toString());
                },
                {error->
                    println(error.message)
                })
            orderVolleyQueue.add(jsonRequest)
    }
}