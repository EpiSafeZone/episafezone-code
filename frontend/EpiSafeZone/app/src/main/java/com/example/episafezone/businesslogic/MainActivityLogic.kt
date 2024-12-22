package com.example.episafezone.businesslogic

import android.util.Log
import com.example.episafezone.MainActivity
import com.example.episafezone.models.Device
import com.example.episafezone.network.MainActivityPetitions
import org.json.JSONObject

object MainActivityLogic {

    fun SaveDevice(device : Device){
        MainActivityPetitions.saveDevice(device)
    }

    fun InitializeQueue(){
        MainActivityPetitions.initializeQueue()
    }

    fun GetPatientsList(){
        MainActivityPetitions.getPatientsList()
    }

    fun ProcessPatientsList(jsonResponse: String){
        //TODO continuar este metodo.
        Log.d("PatientsListResponse", jsonResponse)
        val jsonObject = JSONObject(jsonResponse)

    }
}