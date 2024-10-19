package com.example.episafezone.businesslogic

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.episafezone.ProfileActivity
import com.example.episafezone.databinding.ActivityRegisterManifestationBinding
import com.example.episafezone.models.Manifestation
import com.example.episafezone.network.ManifestationPetitions

object ManifestationLogic {
    fun checkFields(binding: ActivityRegisterManifestationBinding): Boolean {
        return binding.nameTextBox.textView.text.isNotEmpty() &&
                binding.descriptionTextBox.textView.text.isNotEmpty() &&
                binding.procedureTextBox.textView.text.isNotEmpty()
    }

    fun registerManifestation(context: Context, manifestation: Manifestation) {
        if(ManifestationPetitions.addManifestation(manifestation)){
            Toast.makeText(context, "Manifestación registrada correctamente!", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ProfileActivity::class.java)
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Error registrando la manifestación.", Toast.LENGTH_SHORT).show()
        }
    }

    fun createManifestation(binding: ActivityRegisterManifestationBinding) : Manifestation {
        return Manifestation(
            binding.nameTextBox.textView.text.toString(),
            binding.descriptionTextBox.textView.text.toString(),
            binding.procedureTextBox.textView.text.toString()
        )

    }
}