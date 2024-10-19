package com.example.episafezone

import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.ViewGroup
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
    }
}