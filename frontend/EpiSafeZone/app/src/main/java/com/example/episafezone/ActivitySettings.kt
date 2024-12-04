package com.example.episafezone

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.ViewGroup

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
        notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                notificationsConstraintLayout.visibility = View.VISIBLE
            } else {
                notificationsConstraintLayout.visibility = View.GONE
            }
        }
    }
}