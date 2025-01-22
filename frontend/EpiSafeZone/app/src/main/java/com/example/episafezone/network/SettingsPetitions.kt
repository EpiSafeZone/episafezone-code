package com.example.episafezone.network

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.ActivitySettings
import com.example.episafezone.BuildConfig
import com.example.episafezone.models.Patient
import com.example.episafezone.models.User
import org.json.JSONObject

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
                Log.d("GetNoDisturbHours",error.message.toString())
            })
        StartCrisisPetitions.orderVolleyQueue.add(stringRequest)
    }

    fun sharePatient(user : Int, email : String, patient: Patient, register : Boolean,
                     profile : Boolean, medicine : Boolean, tutor: Boolean) {
        val jsonObject = JSONObject()
        jsonObject.put("tutorSharingId",user)
        jsonObject.put("tutorReceivingEmail",email)
        jsonObject.put("patientId",patient.id)
        jsonObject.put("registerCrisisPermission",register)
        jsonObject.put("profilePermission",profile)
        jsonObject.put("medicinePermission",medicine)
        jsonObject.put("tutorPermission",tutor)

        println(jsonObject)

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST,
            "$url/tutor/share",
            jsonObject,
            {response->
            },
            {error->
            })

        orderVolleyQueue.add(jsonRequest)
    }
}