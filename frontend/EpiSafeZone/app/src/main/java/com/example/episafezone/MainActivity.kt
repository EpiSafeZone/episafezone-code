package com.example.episafezone

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.episafezone.databinding.ActivityMainBinding
import com.example.episafezone.fragments.PatientListFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        contextObj = this

        val patientListFragment = PatientListFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayout, patientListFragment)
            commit()
        }

        binding.home.setOnClickListener{

        }

        binding.profile.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentLayout, patientListFragment)
                commit()
            }
        }

        binding.calendar.setOnClickListener{

        }

        binding.chronometer.setOnClickListener{

        }
    }

    companion object {
        private lateinit var contextObj: Context

        fun getContext() : Context {
            return contextObj
        }
    }
}