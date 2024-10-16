package com.example.episafezone

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.episafezone.databinding.ActivityRegisterManifestationBinding

class RegisterManifestationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterManifestationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterManifestationBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.nameTextBox.textView.setText("Nombre")
        binding.descriptionTextBox.textView.setText("Descripción")
        binding.procedureTextBox.textView.setText("Procedimiento")

    }
}