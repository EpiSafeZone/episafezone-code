package com.example.episafezone

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar

class ActivitySettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val notificationsSwitch: SwitchCompat = findViewById(R.id.notificationsSwitch)
        val notificationsConstraintLayout: View = findViewById(R.id.notificationsConstraintLayout)
        val fromButton: Button = findViewById(R.id.fromButton)
        val untilButton: Button = findViewById(R.id.untilButton)
        val fromHourText: TextView = findViewById(R.id.fromHourText)
        val untilHourText: TextView = findViewById(R.id.untilHourText)

        notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                notificationsConstraintLayout.visibility = View.VISIBLE
            } else {
                notificationsConstraintLayout.visibility = View.GONE
            }
        }
        fromButton.setOnClickListener {
            launchTimePicker(fromHourText)
        }
        untilButton.setOnClickListener {
            launchTimePicker(untilHourText)
        }

    }
    private fun launchTimePicker(textView: TextView) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        // Muestra el TimePickerDialog
        val timePicker = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            textView.text = String.format("%02d:%02d", selectedHour, selectedMinute)
        }, hour, minute, true) // Usa true para formato 24h, false para 12h

        timePicker.show()
    }
}