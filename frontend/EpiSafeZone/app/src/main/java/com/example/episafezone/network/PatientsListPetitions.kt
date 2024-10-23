package com.example.episafezone.network

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.ActivityPatientsList
import com.example.episafezone.BuildConfig
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.models.Patient
import org.json.JSONObject

object PatientsListPetitions {
    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(ActivityPatientsList.getContext())
    }

    fun getProfileInfo(patient : Patient){
        val name = patient.id
        println("$url/patient/info/$name")
        val json = JSONObject()
        json.put("id", name)
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, "$url/patient/info/$name", json,
            {response->
                println(response.toString())
                PatientsListLogic.startProfile(response.toString());
            },
            {error->
                println(error.message)
            })
        orderVolleyQueue.add(jsonRequest)
    }
}
