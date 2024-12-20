package com.example.episafezone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.episafezone.databinding.ActivityMainBinding
import com.example.episafezone.fragments.CalendarFragment
import com.example.episafezone.fragments.ChronometerFragment
import com.example.episafezone.fragments.PatientListFragment
import com.example.episafezone.fragments.ProfileFragment
import com.example.episafezone.models.Patient
import java.util.Date
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import android.content.pm.PackageManager
import android.os.Build
import android.preference.PreferenceManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.episafezone.fragments.ChartFragment
import com.example.episafezone.models.Device
import com.example.episafezone.models.User
import com.example.episafezone.network.PatientsListPetitions

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        contextObj = this

        FirebaseApp.initializeApp(this);

        val load = intent.getIntExtra("load", PATIENT_LIST_VIEW)

        if(load == PROFILE_FRAGMENT) {
            changeToProfile()
        } else if (load == CALENDAR_VIEW) {
            changeToCalendar()
        } else if (load == CHRONOMETER_VIEW) {
            changeToStartCrisis(false)
        } else {
            changeToPatientList()
        }

        binding.settings.setOnClickListener{
            val intent = Intent(this, ActivitySettings::class.java)
            startActivity(intent)
        }

        binding.home.setOnClickListener{
            changeToPatientList()
        }

        binding.profile.setOnClickListener{
            changeToProfile()
        }

        binding.calendar.setOnClickListener{
            changeToCalendar()
        }

        binding.chart.setOnClickListener{
            changeToChart()
        }

        binding.chronometer.setOnClickListener{
            changeToStartCrisis(false)
        }
    }

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

    private fun registerDevice(){

        FirebaseMessaging.getInstance().getToken()
            .addOnCompleteListener{task->
                if(!task.isSuccessful){
                    println("Error en register device")
                }else{
                    val token = task.getResult()
                    Log.d("Token",token.toString())
                    val preferences = PreferenceManager.getDefaultSharedPreferences(this)
                    val tokenSaved = preferences.getString("DEVIDE_ID","")
                    if(token != null && (!token.equals(tokenSaved))){
                        val device = Device(0,token,User.getId())

                        PatientsListPetitions.saveDevice(device)
                    }
                }
            }
    }

    fun changeToStartCrisis(startChrono: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, ChronometerFragment(startChrono))
            commit()
        }
    }

    fun changeToCalendar() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, CalendarFragment())
            commit()
        }
    }

    fun changeToChart() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, ChartFragment())
            commit()
        }
    }

    fun changeToProfile() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, ProfileFragment())
            commit()
        }
    }

    fun changeToPatientList() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, PatientListFragment())
            commit()
        }
    }

    companion object {
        const val PROFILE_FRAGMENT = 0
        const val CALENDAR_VIEW = 1
        const val CHRONOMETER_VIEW = 2
        const val PATIENT_LIST_VIEW = 3

        private lateinit var contextObj: Context
        // TODO: Change this when the patient is actually obtained from the recycler view.
        private var patient = Patient(1, "Onofre", "Bustos", 180, 70, Date(), 21, "blue")

        fun getContext() : Context {
            return contextObj
        }

        fun updatePatient(patient: Patient) {
            this.patient = patient
            ProfileFragment.updatePatient(patient)
            CalendarFragment.updatePatient(patient)
            ChronometerFragment.updatePatient(patient)
        }

        fun getPatient() : Patient {
            return patient
        }

        fun changeToStartCrisis() {
            (contextObj as MainActivity).changeToStartCrisis(true)
        }
    }
}