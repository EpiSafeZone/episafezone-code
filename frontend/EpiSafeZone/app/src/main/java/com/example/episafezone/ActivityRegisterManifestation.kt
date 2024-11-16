package com.example.episafezone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.episafezone.businesslogic.ManifestationLogic
import com.example.episafezone.databinding.ActivityRegisterManifestationBinding
import com.example.episafezone.models.Patient

class ActivityRegisterManifestation : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterManifestationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterManifestationBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        contextObj = this

        var patient = intent.getSerializableExtra("patient") as Patient

        binding.nameTextBox.textView.setHint("Nombre")

        binding.descriptionTextBox.textView.setHint("Descripción")

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
            val intent = Intent(this, ActivityProfile::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener {
            if(ManifestationLogic.checkRegisterFields(binding)){
                val manifestation = ManifestationLogic.createRegisterManifestation(binding)
                ManifestationLogic.registerManifestation(manifestation, patient.id)
            } else {
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object{
        private lateinit var contextObj: Context

        fun getContext(): Context {
            return contextObj
        }
    }
}