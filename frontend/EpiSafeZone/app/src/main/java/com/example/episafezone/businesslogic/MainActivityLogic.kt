package com.example.episafezone.businesslogic

import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.example.episafezone.MainActivity
import com.example.episafezone.MainActivity.Companion
import com.example.episafezone.adapter.PatientListAdapter
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

    fun setImage(view: ImageView, bitmap: Bitmap?){
        view.setImageBitmap(bitmap)
    }

    fun ProcessPatientsList(jsonResponse: String){
        Log.d("PatientsListResponse", jsonResponse)
        val jsonArray = JSONArray(jsonResponse)
        val list = ArrayList<Patient>()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id: Int = jsonObject.getInt("id")
            val name: String = jsonObject.getString("name")
            val surname: String = jsonObject.getString("surname")
            list.add(Patient(id, name, surname,0,0,0))
        }
        MainActivity.setAdapter(list)
    }

    fun SetInitialListeners() {
        MainActivity.FirstTimeListers = false

        val mainActivity = MainActivity.getContext() as MainActivity
        val binding = MainActivity.getBinding()

        binding.settings.settingsContainer.setOnClickListener{
            mainActivity.goToSettings()
        }

        binding.addChild.addPatientContainer.setOnClickListener{
            //TODO: Add the logic to add a child
        }

        binding.home.setOnClickListener{
            mainActivity.changeToHome()
        }

        binding.profile.setOnClickListener{
            mainActivity.changeToProfile()
        }

        binding.calendar.setOnClickListener{
            mainActivity.changeToCalendar()
        }

        binding.chronometer.setOnClickListener{
            mainActivity.changeToStartCrisis(false)
        }
    }

    fun EliminateOnClickListenersForTimer(){
        val mainActivity = MainActivity.getContext() as MainActivity
        val binding = MainActivity.getBinding()

        val settings = binding.settings.settingsContainer
        val addPatient = binding.addChild.addPatientContainer
        val patientListView = binding.patientListRecyclerView
        val home = binding.home
        val profile = binding.profile
        val calendar = binding.calendar
        val chronometer = binding.chronometer

        settings.alpha = 0.5f
        settings.setOnClickListener(){
            Toast.makeText(mainActivity, "No puedes acceder a esta opción mientras el cronómetro está activo", Toast.LENGTH_SHORT).show()
        }

        addPatient.alpha = 0.5f
        addPatient.setOnClickListener(){
            Toast.makeText(mainActivity, "No puedes acceder a esta opción mientras el cronómetro está activo", Toast.LENGTH_SHORT).show()
        }

        patientListView.alpha = 0.5f
        PatientListAdapter.setAllNotClickAble()
        patientListView.setOnClickListener(){
            Toast.makeText(mainActivity, "No puedes acceder a esta opción mientras el cronómetro está activo", Toast.LENGTH_SHORT).show()
        }

        home.alpha = 0.5f
        home.setOnClickListener(){
            Toast.makeText(mainActivity, "No puedes acceder a esta opción mientras el cronómetro está activo", Toast.LENGTH_SHORT).show()
        }

        profile.alpha = 0.5f
        profile.setOnClickListener(){
            Toast.makeText(mainActivity, "No puedes acceder a esta opción mientras el cronómetro está activo", Toast.LENGTH_SHORT).show()
        }

        calendar.alpha = 0.5f
        calendar.setOnClickListener(){
            Toast.makeText(mainActivity, "No puedes acceder a esta opción mientras el cronómetro está activo", Toast.LENGTH_SHORT).show()
        }

        chronometer.alpha = 0.5f
        chronometer.setOnClickListener(){
            Toast.makeText(mainActivity, "No puedes acceder a esta opción mientras el cronómetro está activo", Toast.LENGTH_SHORT).show()
        }
    }

    fun EnableOnClickListenersForTimer(){
        val mainActivity = MainActivity.getContext() as MainActivity
        val binding = MainActivity.getBinding()

        val settings = binding.settings.settingsContainer
        val addPatient = binding.addChild.addPatientContainer
        val patientListView = binding.patientListRecyclerView
        val home = binding.home
        val profile = binding.profile
        val calendar = binding.calendar
        val chronometer = binding.chronometer

        settings.alpha = 1f
        settings.setOnClickListener(){
            mainActivity.goToSettings()
        }

        addPatient.alpha = 1f
        addPatient.setOnClickListener(){
            // TODO: Implementar la lógica para agregar un paciente
        }

        patientListView.alpha = 1f
        PatientListAdapter.setAllClickAble()
        patientListView.setOnClickListener(){}

        home.alpha = 1f
        home.setOnClickListener(){
            mainActivity.changeToHome()
        }

        profile.alpha = 1f
        profile.setOnClickListener(){
            mainActivity.changeToProfile()
        }

        calendar.alpha = 1f
        calendar.setOnClickListener(){
            mainActivity.changeToCalendar()
        }

        chronometer.alpha = 1f
        chronometer.setOnClickListener(){
            mainActivity.changeToStartCrisis(false)
        }
    }
}