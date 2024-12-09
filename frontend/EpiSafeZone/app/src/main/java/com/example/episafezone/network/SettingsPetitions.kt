package com.example.episafezone.network

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.ActivitySettings
import com.example.episafezone.BuildConfig

object SettingsPetitions {
    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(ActivitySettings.getContext())
    }

    fun getNoDisturbHours() { //TODO: Handle correctly. This is just a skeleton
        //val id = patient.id
        val stringRequest = StringRequest(
            Request.Method.GET, "${SettingsPetitions.url}/tutor/editHours",
            {response->
                Log.d("GetNoDisturbHours",response.toString())
            },
            {error->
                Log.d("GetPatientManifestations",error.message.toString())
            })
        StartCrisisPetitions.orderVolleyQueue.add(stringRequest)
    }
}