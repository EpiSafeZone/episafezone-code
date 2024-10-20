package com.example.episafezone.network

import android.content.Context
import android.widget.Toast
import com.example.episafezone.BuildConfig
import com.example.episafezone.ProfileActivity
import com.example.episafezone.models.Manifestation

object ManifestationPetitions {

    private const val myIP = BuildConfig.API_LINK
    private val url = "http://$myIP"

    lateinit var orderVolleyQueue: RequestQueue

    fun initializeQueue(){
        //TODO: Call this method in the profile activity
        orderVolleyQueue = Volley.newRequestQueue(ProfileActivity.getContext())
    }

    //TODO: Add as a parameter of this method a reference to the patient
    fun getManifestations() :  List<Manifestation> {
        //TODO: Implement this method
        return emptyList()
    }

    fun addManifestation(manifestation: Manifestation) : Boolean {
        //TODO: Implement this method
        return true
    }

    fun editManifestation(manifestationToModify: Manifestation,
                          modifiedManifestation: Manifestation) :Boolean {
        //TODO: Implement this method
        return true
    }
}