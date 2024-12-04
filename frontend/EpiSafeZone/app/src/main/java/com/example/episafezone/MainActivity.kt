package com.example.episafezone

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.episafezone.businesslogic.ChronometerLogic
import com.example.episafezone.databinding.ActivityMainBinding
import com.example.episafezone.fragments.CalendarFragment
import com.example.episafezone.fragments.ChronometerFragment
import com.example.episafezone.fragments.PatientListFragment
import com.example.episafezone.fragments.ProfileFragment
import com.example.episafezone.models.Patient
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        contextObj = this

        val load = intent.getStringExtra("load") ?: ""

        val patientListFragment = PatientListFragment()

        if(load == "profile") {
            changeToProfile()
        } else if (load == "calendar") {
            changeToCalendar()
        } else if (load == "chronometer") {
            changeToStartCrisis(false)
        } else {
            changeToPatientList()
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