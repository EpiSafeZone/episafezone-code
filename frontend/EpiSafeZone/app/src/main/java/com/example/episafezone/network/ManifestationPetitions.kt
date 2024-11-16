package com.example.episafezone.network

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.episafezone.BuildConfig
import com.example.episafezone.ActivityProfile
import com.example.episafezone.businesslogic.ManifestationLogic
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.models.Manifestation
import com.example.episafezone.models.Patient
import org.json.JSONObject

object ManifestationPetitions {

    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        orderVolleyQueue = Volley.newRequestQueue(ActivityProfile.getContext())
    }

    fun addManifestation(manifestation: Manifestation, patientId: Int) {
        val json = JSONObject()
        json.put("name", manifestation.name)
        json.put("description", manifestation.description)
        json.put("patientId", patientId)
        //TODO: Terminar el JSON con pasos a seguir cuando se implemente.
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, "$url/manifestation/create", json,
            { response ->
                ManifestationLogic.responseRegisterManifestation(true)
            },
            { error ->
                ManifestationLogic.responseRegisterManifestation(false)
            })
        orderVolleyQueue.add(jsonRequest)
    }

    fun editManifestation(modifiedManifestation: Manifestation) {
        val json = JSONObject()
        json.put("name", modifiedManifestation.name)
        json.put("description", modifiedManifestation.description)
        //TODO: Terminal el JSON con pasos a seguir cuando se implemente.
        val jsonRequest = JsonObjectRequest(
            Request.Method.PUT, "$url/update/${modifiedManifestation.id}", json,
            { response ->
                ManifestationLogic.responseEditManifestation(true)
            },
            { error ->
                ManifestationLogic.responseEditManifestation(false)
            })
        orderVolleyQueue.add(jsonRequest)
    }

    fun deleteManifestation(manifestation: Manifestation){
        val jsonRequest = StringRequest(
            Request.Method.DELETE, "$url/delete/${manifestation.id}",
            { response ->
                ManifestationLogic.responseDeleteManifestation(true, manifestation)
            },
            { error ->
                ManifestationLogic.responseDeleteManifestation(false, manifestation)
            })
        orderVolleyQueue.add(jsonRequest)
    }
}