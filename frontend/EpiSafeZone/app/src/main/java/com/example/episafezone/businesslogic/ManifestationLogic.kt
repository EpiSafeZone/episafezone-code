package com.example.episafezone.businesslogic

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.episafezone.ActivityEditManifestation
import com.example.episafezone.ActivityRegisterManifestation
import com.example.episafezone.MainActivity
import com.example.episafezone.R
import com.example.episafezone.databinding.ActivityEditManifestationBinding
import com.example.episafezone.databinding.ActivityRegisterManifestationBinding
import com.example.episafezone.fragments.ProfileFragment
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

    fun registerManifestation(manifestation: Manifestation, patientId: Int) {
        ManifestationPetitions.addManifestation(manifestation, patientId)
    }

    fun responseRegisterManifestation(success: Boolean){
        val context = ActivityRegisterManifestation.getContext()
        if(success){
            Toast.makeText(context, "Manifestación registrada correctamente!", Toast.LENGTH_SHORT).show()
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

    fun createEditManifestation(binding: ActivityEditManifestationBinding, id: Int) : Manifestation {
        return Manifestation(
            id,
            binding.nameTextBox.textView.text.toString(),
            binding.descriptionTextBox.textView.text.toString(),
            binding.procedureTextBox.textView.text.toString()
        )
    }

    fun loadEditManifestation(context: Context,manifestation: Manifestation){
        val intent = Intent(context, ActivityEditManifestation::class.java)
        intent.putExtra("id", manifestation.id)
        context.startActivity(intent)
    }

    fun editManifestation(modifiedManifestation: Manifestation) {
        ManifestationPetitions.editManifestation(modifiedManifestation)
    }

    fun responseEditManifestation(success: Boolean) {
        val context = ActivityEditManifestation.getContext()
        if(success){
            Toast.makeText(context, "Manifestación editada correctamente!", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("load", "profile")
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Error editando la manifestación.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteManifestation(manifestation: Manifestation) {
        ManifestationPetitions.deleteManifestation(manifestation)
        var oldList = ProfileFragment.getListManifestations()
        oldList.remove(manifestation)
        var newList = oldList
        ProfileFragment.updateListOfManifestations(newList)
    }

    fun responseDeleteManifestation(success: Boolean, manifestation: Manifestation) {
        val context = ActivityEditManifestation.getContext()
        if(success){
            Toast.makeText(context, "Manifestación eliminada correctamente!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Error eliminando la manifestación.", Toast.LENGTH_SHORT).show()
            var oldList = ProfileFragment.getListManifestations()
            oldList.add(manifestation)
            var newList = oldList
            ProfileFragment.updateListOfManifestations(newList)
        }
    }

    fun showCustomDialogBox(context: Context, manifestation: Manifestation){
        val dialog = Dialog(context)
        var message = "Esta seguro que quiere eliminar esta manifestación?"
        dialog.setTitle("CONFIRMATION")
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.pop_up_confirmation)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val messageBox = dialog.findViewById<TextView>(R.id.textViewErrorText)
        val btnOk = dialog.findViewById<Button>(R.id.buttonOkPopUp)
        val btnCancel = dialog.findViewById<Button>(R.id.buttonCancelPopUp)

        btnOk.setOnClickListener{
            deleteManifestation(manifestation)
            dialog.dismiss()
        }

        btnCancel.setOnClickListener{
            dialog.dismiss()
        }

        messageBox.text = message

        dialog.show()
    }
}