package com.example.episafezone.businesslogic

import android.util.Log
import com.example.episafezone.MainActivity
import com.example.episafezone.models.Device
import com.example.episafezone.models.Patient
import com.example.episafezone.network.MainActivityPetitions
import org.json.JSONArray
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
        val jsonArray = JSONArray(jsonResponse)
        val list = ArrayList<Patient>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id: Int = jsonObject.getInt("id")
            val name: String = jsonObject.getString("name")
            val surname: String = jsonObject.getString("surname")
            // TODO: Descomentar lo siguiente cuando se haya implementado en el back
            //val height: Int = jsonObject.getInt("height")
            //val weight: Int = jsonObject.getInt("weight")
            //val age: Int = jsonObject.getInt("age")
            // TODO: Eliminar lo siguiente cuando se implemente lo faltante en el back
            val height: Int = 0
            val weight: Int = 0
            val age: Int = 0
            val imageUrl: String = jsonObject.getString("imageUrl")
            list.add(Patient(id, name, surname, height, weight, age, imageUrl))
        }
        MainActivity.setAdapter(list)
    }
}