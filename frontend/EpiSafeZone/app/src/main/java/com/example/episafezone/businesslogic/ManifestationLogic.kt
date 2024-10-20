package com.example.episafezone.businesslogic

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.episafezone.ActivityEditManifestation
import com.example.episafezone.ActivityProfile
import com.example.episafezone.ActivityRegisterManifestation
import com.example.episafezone.databinding.ActivityEditManifestationBinding
import com.example.episafezone.databinding.ActivityRegisterManifestationBinding
import com.example.episafezone.models.Manifestation
import com.example.episafezone.network.ManifestationPetitions

object ManifestationLogic {

    fun checkRegisterFields(binding: ActivityRegisterManifestationBinding): Boolean {
        return binding.nameTextBox.textView.text.isNotEmpty() &&
                binding.descriptionTextBox.textView.text.isNotEmpty() &&
                binding.procedureTextBox.textView.text.isNotEmpty()
    }

    fun checkEditFields(binding: ActivityEditManifestationBinding): Boolean {
        return binding.nameTextBox.textView.text.isNotEmpty() &&
                binding.descriptionTextBox.textView.text.isNotEmpty() &&
                binding.procedureTextBox.textView.text.isNotEmpty()
    }

    fun registerManifestation(manifestation: Manifestation) {
        ManifestationPetitions.addManifestation(manifestation)
    }

    fun responseRegisterManifestation(success: Boolean){
        val context = ActivityRegisterManifestation.getContext()
        if(success){
            Toast.makeText(context, "Manifestación registrada correctamente!", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ActivityProfile::class.java)
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Error registrando la manifestación.", Toast.LENGTH_SHORT).show()
        }
    }

    fun createRegisterManifestation(binding: ActivityRegisterManifestationBinding) : Manifestation {
        return Manifestation(
            binding.nameTextBox.textView.text.toString(),
            binding.descriptionTextBox.textView.text.toString(),
            binding.procedureTextBox.textView.text.toString()
        )
    }

    fun createEditManifestation(binding: ActivityEditManifestationBinding) : Manifestation {
        return Manifestation(
            binding.nameTextBox.textView.text.toString(),
            binding.descriptionTextBox.textView.text.toString(),
            binding.procedureTextBox.textView.text.toString()
        )
    }

    fun loadEditManifestation(context: Context,manifestation: Manifestation){
        val intent = Intent(context, ActivityEditManifestation::class.java)
        context.startActivity(intent)
    }

    fun editManifestation(manifestationToModify: Manifestation,
                          modifiedManifestation: Manifestation) {
        ManifestationPetitions.editManifestation(manifestationToModify, modifiedManifestation)
    }

    fun responseEditManifestation(success: Boolean) {
        val context = ActivityEditManifestation.getContext()
        if(success){
            Toast.makeText(context, "Manifestación editada correctamente!", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ActivityProfile::class.java)
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Error editando la manifestación.", Toast.LENGTH_SHORT).show()
        }
    }
}