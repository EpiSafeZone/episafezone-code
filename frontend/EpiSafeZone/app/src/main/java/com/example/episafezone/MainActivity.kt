package com.example.episafezone

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.episafezone.databinding.ActivityMainBinding
import com.example.episafezone.fragments.CalendarFragment
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
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentLayout, ProfileFragment(patient))
                commit()
            }
        } else if (load == "calendar") {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentLayout, CalendarFragment(patient))
                commit()
            }
        } else if (load == "startCrisis") {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentLayout, ChronometerFragment(patient))
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentLayout, patientListFragment)
                commit()
            }
        }

        binding.home.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentLayout, patientListFragment)
                commit()
            }
        }

        binding.profile.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentLayout, ProfileFragment(patient))
                commit()
            }
        }

        binding.calendar.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentLayout, CalendarFragment(patient))
                commit()
            }
        }

        binding.chronometer.setOnClickListener{

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
        }

        fun changeToStartCrisis() {
            // TODO: Implement this.
        }
    }
}