package com.example.episafezone.network

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.ActivityProfile
import com.example.episafezone.BuildConfig
import com.example.episafezone.models.Patient
import org.json.JSONArray
import org.json.JSONObject

object ProfilePetitions {
    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(ActivityProfile.getContext())
    }

    fun getProfileInfo(patient : Patient){
        val name = patient.id
        println("${url}/patient/info/$name")
        val json = JSONObject()
        json.put("id", name)
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, "${url}/patient/info/$name", json,
            {response->
                Log.d("GetProfileInfoResponse",response.toString())
                ActivityProfile.startProfile(response.toString());
            },
            {error->
                Log.d("GetProfileError",error.message.toString())
            })
        orderVolleyQueue.add(jsonRequest)
    }

}