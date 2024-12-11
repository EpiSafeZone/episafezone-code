package com.example.episafezone.network

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.BuildConfig
import com.example.episafezone.businesslogic.CalendarLogic
import com.example.episafezone.businesslogic.ChartLogic
import com.example.episafezone.fragments.ChartFragment
import com.example.episafezone.models.Patient
import org.json.JSONObject

object ChartPetitions {
    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(ChartFragment.getContext())
    }

    fun getManifestationList(patient: Patient){
        println("${url}/patient/crisis/${patient.id}")
        val json = JSONObject()
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, "${url}/patient/numMani/${patient.id}", json,
            {response->
                println(response.toString())
            },
            {error->
                println(error.message)
            })
        orderVolleyQueue.add(jsonRequest)
    }

    fun getCrisisList(patient: Patient){
        println("${url}/patient/crisis/${patient.id}")
        val json = JSONObject()
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, "${url}/patient/crisisWeek/${patient.id}", json,
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