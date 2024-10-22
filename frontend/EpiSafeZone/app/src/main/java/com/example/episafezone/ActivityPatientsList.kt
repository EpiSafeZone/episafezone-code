package com.example.episafezone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.episafezone.businesslogic.PatientsListLogic
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.databinding.ActivityPatientsListBinding
import com.example.episafezone.network.PatientsListPetitions
import com.example.episafezone.network.ProfilePetitions

class ActivityPatientsList : AppCompatActivity() {

    private lateinit var binding: ActivityPatientsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contextObj = this
        binding = ActivityPatientsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PatientsListPetitions.initializeQueue();

    }

    companion object{
        private lateinit var contextObj: Context

        fun getContext() : Context {
            return contextObj
        }

        fun startProfile(returns : String){
            val intent = Intent(contextObj,ActivityProfile::class.java)
            contextObj.startActivity(intent)
        }
    }

}