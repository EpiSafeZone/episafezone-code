package com.example.episafezone

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.adapter.PatientListAdapter
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.businesslogic.StartCrisisLogic
import com.example.episafezone.databinding.ActivityPatientsListBinding
import com.example.episafezone.models.Device
import com.example.episafezone.models.Patient
import com.example.episafezone.models.User
import com.example.episafezone.network.PatientsListPetitions
import com.example.episafezone.network.StartCrisisPetitions
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import java.util.Date

class ActivityPatientsList : AppCompatActivity() {

    private lateinit var binding: ActivityPatientsListBinding

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
            }
        }
        this.registerDevice();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this);
        contextObj = this
        binding = ActivityPatientsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listPatient : List<Patient> = PatientsListLogic.getPatientListInfo();
        binding.PatientRecyclerView.adapter = PatientListAdapter(this, listPatient)
        binding.PatientRecyclerView.layoutManager = LinearLayoutManager(this)

        PatientsListPetitions.initializeQueue();
    }

    companion object{
        private lateinit var contextObj: Context
        fun getContext() : Context {
            return contextObj
        }

        fun startProfileActivity(patient: Patient){
            val intent = Intent(contextObj,ActivityProfile::class.java)
            intent.putExtra("patient", patient)
            contextObj.startActivity(intent)
        }

        fun loadStartCrisis(patient : Patient){
            val intent = Intent(contextObj,ActivityStartCrisis::class.java)
            intent.putExtra("patient", patient)
            contextObj.startActivity(intent)
        }
        fun startRegisterManifestation(patient: Patient){
            val intent = Intent(contextObj, ActivityRegisterManifestation::class.java)
            intent.putExtra("patient", patient)
            contextObj.startActivity(intent)
        }
    }

    private fun registerDevice(){

        FirebaseMessaging.getInstance().getToken()
            .addOnCompleteListener{task->
                if(!task.isSuccessful){
                    println("Error en register device")
                }else{
                    val token = task.getResult()
                    println(token+"----------------------------------------------")
                    val preferences = PreferenceManager.getDefaultSharedPreferences(this)
                    val tokenSaved = preferences.getString("DEVIDE_ID","")
                    if(token != null && (!token.equals(tokenSaved))){
                        //TODO(Hacer inicio de sesión temporal para que user sea dinámico)
                        val user1 = User(1);
                        val device = Device(0,token,user1.id)

                        PatientsListPetitions.saveDevice(device)
                    }
                }

        }

    }



}