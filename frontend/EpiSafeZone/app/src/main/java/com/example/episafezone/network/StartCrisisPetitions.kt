package com.example.episafezone.network

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.ActivityPatientsList
import com.example.episafezone.ActivityProfile
import com.example.episafezone.ActivityStartCrisis
import com.example.episafezone.BuildConfig
import com.example.episafezone.businesslogic.StartCrisisLogic
import com.example.episafezone.models.Patient
import org.json.JSONObject

object StartCrisisPetitions {

    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(ActivityStartCrisis.getContext())
    }

    fun getPatientManifestations(patient: Patient) {
        //TODO Arreglar esta parte de crear la peticion, no las respuestas.
        val name = patient.id
        println("${url}/patient/info/$name")
        val json = JSONObject()
        json.put("id", name)
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, "${url}/patient/info/$name", json,
            {response->
                //TODO: Comprobar que lo que me llega se llama "manifestations"
                println(response.toString())
                StartCrisisLogic.setUpInfo(response.toString())
            },
            {error->
                println(error.message)
            })
        orderVolleyQueue.add(jsonRequest)
    }
}