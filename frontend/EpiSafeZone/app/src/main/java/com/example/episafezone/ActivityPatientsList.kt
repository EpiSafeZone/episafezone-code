package com.example.episafezone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.databinding.ActivityPatientsListBinding

class ActivityPatientsList : AppCompatActivity() {

    private lateinit var binding: ActivityPatientsListBinding
    private var profileLogic = ProfileLogic();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contextObj=this;


        binding.button.setOnClickListener(){
            profileLogic.sendPetition()
        }
    }

    companion object{
        private lateinit var contextObj: Context

        fun getContext() : Context {
            return contextObj
        }

        fun startProfile(){
            val intent = Intent(contextObj,ActivityProfile::class.java)
            contextObj.startActivity(intent)
        }
    }

}