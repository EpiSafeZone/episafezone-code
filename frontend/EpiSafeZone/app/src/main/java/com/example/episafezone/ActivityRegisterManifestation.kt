package com.example.episafezone

import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.episafezone.databinding.ActivityRegisterManifestationBinding

class ActivityRegisterManifestation : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterManifestationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterManifestationBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.nameTextBox.textView.setText("Nombre")

        binding.descriptionTextBox.textView.setText("Descripción")

        binding.procedureTextBox.textView.setText("Procedimiento")
        binding.procedureTextBox.textView.setLines(3);
        binding.procedureTextBox.textView.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
        binding.procedureTextBox.textView.gravity = Gravity.TOP
        binding.procedureTextBox.textView.isVerticalScrollBarEnabled = true
    }
}