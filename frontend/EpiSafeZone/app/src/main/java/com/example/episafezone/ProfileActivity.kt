package com.example.episafezone

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.episafezone.adapter.ManifestAdapter
import com.example.episafezone.adapter.MedicineAdapter
import com.example.episafezone.businesslogic.ProfileLogic
import com.example.episafezone.databinding.ActivityProfileBinding


class ProfileActivity : AppCompatActivity() {
    
    lateinit var binding : ActivityProfileBinding
    val profileLogic = ProfileLogic();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater);
        setContentView(binding.root)

        val profile = profileLogic.getProfileInfo();

        val listMedicine : List<String> = profileLogic.getMedicineInfo();
        binding.medicamentsRecycler.adapter =  MedicineAdapter(listMedicine,this);
        binding.medicamentsRecycler.layoutManager = LinearLayoutManager(this)

        val listManifest : List<String> = profileLogic.getManifestInfo();
        binding.manifestRecycler.adapter = ManifestAdapter(this,listManifest);
        binding.manifestRecycler.layoutManager = LinearLayoutManager(this);

    }
}