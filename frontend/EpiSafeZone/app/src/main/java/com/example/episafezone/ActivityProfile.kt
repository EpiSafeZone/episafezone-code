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
import com.example.episafezone.network.ProfilePetitions


class ActivityProfile : AppCompatActivity() {
    
    lateinit var binding : ActivityProfileBinding
    val profileLogic = ProfileLogic();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater);
        setContentView(binding.root)
        contextObj=this;

        val listMedicine : List<Medication> = profileLogic.getMedicationInfo();
        binding.medicamentsRecycler.adapter =  MedicationAdapter(listMedicine,this);
        binding.medicamentsRecycler.layoutManager = LinearLayoutManager(this)

        val listManifest : List<Manifestation> = profileLogic.getManifestInfo();
        binding.manifestRecycler.adapter = ManifestAdapter(this,listManifest);
        binding.manifestRecycler.layoutManager = LinearLayoutManager(this);

        binding.addMedButt.setOnClickListener(){
            val intent = Intent(this,ActivityAddMedication::class.java)
            startActivity(intent)
        }

        binding.addManifButt.setOnClickListener{
            val intent = Intent(this,ActivityRegisterManifestation::class.java)
            startActivity(intent)
        }

        ProfilePetitions.initializeQueue()
    }

    companion object{
        private lateinit var contextObj: Context

        fun getContext() : Context{
            return contextObj
        }
    }
}