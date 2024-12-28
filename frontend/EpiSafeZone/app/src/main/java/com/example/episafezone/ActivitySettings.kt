package com.example.episafezone

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.episafezone.network.SettingsPetitions
import java.util.Calendar

class ActivitySettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        contextObj = this
        SettingsPetitions.initializeQueue()

        // Return back
        val returnBackButton: Button = findViewById(R.id.returnBackButton)
        returnBackButton.setOnClickListener {
            finish()
        }

        // Notifications
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

        // Share Profile
        val shareProfileSwitch: SwitchCompat = findViewById(R.id.shareProfileSwitch)
        val shareProfileConstraintLayout: View = findViewById(R.id.shareProfileConstraintLayout)
        val permission1CheckBox: CheckBox = findViewById(R.id.permission1CheckBox)
        val permission2CheckBox: CheckBox = findViewById(R.id.permission2CheckBox)
        val permission3CheckBox: CheckBox = findViewById(R.id.permission3CheckBox)
        val permission4CheckBox: CheckBox = findViewById(R.id.permission4CheckBox)
        val shareEmailEditText: TextView = findViewById(R.id.shareEmailEditText)
        val shareProfileButton: Button = findViewById(R.id.shareProfileButton)
        shareProfileSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                shareProfileConstraintLayout.visibility = View.VISIBLE
            } else {
                shareProfileConstraintLayout.visibility = View.GONE
            }
        }
        shareProfileButton.setOnClickListener {
            if (shareEmailEditText.text.isEmpty()) {
                Toast.makeText(this, "Debes ingresar un email", Toast.LENGTH_SHORT).show()
            } else {
                //TODO: Share profile
                Toast.makeText(this, "¡Perfil compartido!", Toast.LENGTH_SHORT).show()

                // Set everything to default
                permission1CheckBox.isChecked = false
                permission2CheckBox.isChecked = false
                permission3CheckBox.isChecked = false
                permission4CheckBox.isChecked = false
                shareEmailEditText.text = ""
            }
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
    companion object {
        private lateinit var contextObj: Context
        fun getContext(): Context {
            return contextObj
        }
    }
}