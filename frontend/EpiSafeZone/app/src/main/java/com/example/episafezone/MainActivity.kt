package com.example.episafezone

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.episafezone.databinding.ActivityMainBinding
import com.example.episafezone.fragments.CalendarFragment
import com.example.episafezone.fragments.ChartFragment
import com.example.episafezone.fragments.ChronometerFragment
import com.example.episafezone.fragments.HomeFragment
import com.example.episafezone.fragments.ProfileFragment
import com.example.episafezone.models.Device
import com.example.episafezone.models.Patient
import com.example.episafezone.models.User
import com.example.episafezone.network.ChartPetitions
import com.example.episafezone.network.ManifestationPetitions
import com.example.episafezone.network.MedicationPetitions
import com.example.episafezone.network.PatientsListPetitions
import com.example.episafezone.network.ProfilePetitions
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import android.content.pm.PackageManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.adapter.PatientListAdapter
import com.example.episafezone.businesslogic.MainActivityLogic
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.fragments.ChartFragment
import com.example.episafezone.fragments.HomeFragment.Companion
import com.example.episafezone.fragments.decorations.MarginItemDecoration
import com.example.episafezone.models.Device
import com.example.episafezone.models.User
import kotlin.system.exitProcess
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        contextObj = this

        MainActivityLogic.InitializeQueue()

        MainActivityLogic.GetPatientsList()

        FirebaseApp.initializeApp(this);
        val locale = Locale("es") // Idioma Español
        Locale.setDefault(locale)

        ProfilePetitions.initializeQueue()
        MedicationPetitions.initializeQueue()
        ManifestationPetitions.initializeQueue()
        ChartPetitions.initializeQueue()

        loadInitialFragment(0)

        binding.settings.settingsContainer.setOnClickListener{
            val intent = Intent(this, ActivitySettings::class.java)
            startActivity(intent)
        }

        binding.addChild.addPatientContainer.setOnClickListener{
            //TODO: Add the logic to add a child
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

    private fun loadInitialFragment(retryCounter: Int) {
        Handler(Looper.getMainLooper()).postDelayed({
            if(patient == null) {
                if(retryCounter > 2) {
                    Log.e("CargaPacientes","Error al cargar los pacientes")
                    Toast.makeText(this, "Error al cargar los pacientes", Toast.LENGTH_SHORT).show()
                    exitProcess(1)
                }
                loadInitialFragment(retryCounter + 1)
            } else{
                val load = intent.getIntExtra("load", HOME_VIEW)
                when (load) {
                    PROFILE_VIEW -> changeToProfile()
                    CALENDAR_VIEW -> changeToCalendar()
                    CHRONOMETER_VIEW -> changeToStartCrisis(false)
                    else -> changeToPatientList()
                }
            }
        }, 500)
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

                        MainActivityLogic.SaveDevice(device)
                    }
                }
            }
    }

    fun changeToStartCrisis(startChrono: Boolean) {
        setAllUnselected()
        binding.chronometer.setImageResource(R.drawable.chrono_selected)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, ChronometerFragment(startChrono))
            commit()
        }

        currentFragment = CHRONOMETER_VIEW
    }

    fun changeToCalendar() {
        setAllUnselected()
        binding.calendar.setImageResource(R.drawable.calendar_selected)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, CalendarFragment())
            commit()
        }

        currentFragment = CALENDAR_VIEW
    }

    fun changeToProfile() {
        setAllUnselected()
        binding.profile.setImageResource(R.drawable.profile_selected)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, ProfileFragment())
            commit()
        }

        currentFragment = PROFILE_VIEW
    }

    fun changeToPatientList() {
        setAllUnselected()
        binding.home.setImageResource(R.drawable.home_selected)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, HomeFragment())
            commit()
        }

        currentFragment = HOME_VIEW
    }

    fun setAllUnselected(){
        binding.home.setImageResource(R.drawable.home_not_selected)
        binding.profile.setImageResource(R.drawable.profile_not_selected)
        binding.calendar.setImageResource(R.drawable.calendar_not_selected)
        binding.chronometer.setImageResource(R.drawable.chrono_not_selected)
    }

    companion object {
        private lateinit var binding : ActivityMainBinding
        private lateinit var contextObj: Context
        private lateinit var listPatient : List<Patient>

        const val PROFILE_VIEW = 0
        const val CALENDAR_VIEW = 1
        const val CHRONOMETER_VIEW = 2
        const val HOME_VIEW = 3

        private var patient : Patient? = null

        private var currentFragment : Int = HOME_VIEW

        private var firstTimeAdapter = true

        fun getContext() : Context {
            return contextObj
        }

        fun updatePatient(patient: Patient) {
            val newListPatient = listPatient.toMutableList()
            newListPatient.remove(patient)
            newListPatient.add(0, patient)
            setAdapter(newListPatient)
        }

        fun getPatient() : Patient {
            return patient!!
        }

        fun changeToStartCrisis() {
            (contextObj as MainActivity).changeToStartCrisis(true)
        }

        fun setAdapter(listPatient : List<Patient>){
            this.listPatient = listPatient
            binding.patientListRecyclerView.adapter = PatientListAdapter(contextObj, listPatient)
            if(firstTimeAdapter) {
                binding.patientListRecyclerView.apply {
                    addItemDecoration(MarginItemDecoration(-20)) // Adjust the margin start value
                    setPadding(
                        20,
                        0,
                        0,
                        0
                    ) // Add padding to the start to ensure the first item is fully visible
                    clipToPadding = false
                }
                firstTimeAdapter = false
            } else {
                when (currentFragment) {
                    PROFILE_VIEW -> (contextObj as MainActivity).changeToProfile()
                    CALENDAR_VIEW -> (contextObj as MainActivity).changeToCalendar()
                    CHRONOMETER_VIEW -> (contextObj as MainActivity).changeToStartCrisis(false)
                    HOME_VIEW -> (contextObj as MainActivity).changeToPatientList()
                }
            }
            binding.patientListRecyclerView.layoutManager = LinearLayoutManager(contextObj,LinearLayoutManager.HORIZONTAL,false)

            patient = listPatient[0]
        }

        fun changeToChart(){
            (contextObj as MainActivity).changeToChart()
        }

        fun changeToCalendar(){
            (contextObj as MainActivity).changeToCalendar()
        }
    }
}