package com.example.episafezone

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.episafezone.adapter.ManifestAdapter
import com.example.episafezone.businesslogic.ManifestationLogic
import com.example.episafezone.databinding.ActivityEditManifestationBinding

class ActivityEditManifestation : AppCompatActivity() {

    private lateinit var binding: ActivityEditManifestationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditManifestationBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.nameTextBox.textView.setText("Nombre")

        binding.descriptionTextBox.textView.setText("Descripción")

        binding.procedureTextBox.textView.apply {
            hint = "Procedimiento"
            setLines(3)
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
            gravity = Gravity.TOP
            isVerticalScrollBarEnabled = true
            minLines = 3
            maxLines = 10
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        }

        binding.cancelButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.acceptButton.setOnClickListener {
            if(ManifestationLogic.checkEditFields(binding)){
                val manifestationModified = ManifestationLogic.createEditManifestation(binding)
                ManifestationLogic.editManifestation(this, ManifestAdapter.getCurrentManifestation(), manifestationModified)
            } else {
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }
}