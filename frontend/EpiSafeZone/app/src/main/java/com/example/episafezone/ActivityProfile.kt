package com.example.episafezone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.episafezone.adapter.ManifestAdapter
import com.example.episafezone.adapter.MedicationAdapter
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.databinding.ActivityProfileBinding
import com.example.episafezone.models.Medication
import com.example.episafezone.models.Manifestation
import com.example.episafezone.network.ManifestationPetitions
import com.example.episafezone.network.ProfilePetitions


class ActivityProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater);
        setContentView(binding.root)
        contextObj=this;

        val json = intent.getSerializableExtra("json") as String
        ProfileLogic.setUpInfo(json,binding,this)


        ProfileLogic.getManifestInfo();

        binding.addMedButt.setOnClickListener(){
            val intent = Intent(this,ActivityAddMedication::class.java)
            startActivity(intent)
        }

        binding.addManifButt.setOnClickListener{
            val intent = Intent(this,ActivityRegisterManifestation::class.java)
            startActivity(intent)
        }

        ProfilePetitions.initializeQueue()
        ManifestationPetitions.initializeQueue()
    }

    companion object{

        private lateinit var contextObj: Context
        private lateinit var binding: ActivityProfileBinding

        fun getContext() : Context{
            return contextObj
        }

        fun updateListOfManifestations(list: List<Manifestation>){
            binding.manifestRecycler.adapter = ManifestAdapter(contextObj,list);
            binding.manifestRecycler.layoutManager = LinearLayoutManager(contextObj);
        }
    }
}