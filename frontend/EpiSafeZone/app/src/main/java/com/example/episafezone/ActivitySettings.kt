package com.example.episafezone

import android.app.TimePickerDialog
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
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
        val notificationsSwitch: ConstraintLayout = findViewById(R.id.notificationsSwitchConstraintLayout)
        val notificationsConstraintLayout: View = findViewById(R.id.notificationsConstraintLayout)
        val notificationsSwitchImageView: ImageView = findViewById(R.id.notificationsSwitchImageView)
        val fromButton: Button = findViewById(R.id.fromButton)
        val untilButton: Button = findViewById(R.id.untilButton)
        val fromHourText: TextView = findViewById(R.id.fromHourText)
        val untilHourText: TextView = findViewById(R.id.untilHourText)

        var isNotificationsSelected = false

        notificationsSwitch.setOnClickListener {
            isNotificationsSelected = !isNotificationsSelected

            if (isNotificationsSelected) {
                notificationsConstraintLayout.visibility = View.VISIBLE
                notificationsSwitchImageView.animate().rotation(90f).setDuration(500).start()
            } else {
                notificationsConstraintLayout.visibility = View.GONE
                notificationsSwitchImageView.animate().rotation(0f).setDuration(500).start()
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

        //Asignar colores a los checkbox para que sean visibles
        // Crear un array de colores para cada estado
        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked), // Estado marcado
            intArrayOf(-android.R.attr.state_checked) // Estado no marcado
        )

        // Crear un array de colores correspondientes a los estados
        val colors = intArrayOf(
            ContextCompat.getColor(this, R.color.epiBlackBackground), // epiBlackBackground cuando está marcado
            ContextCompat.getColor(this, R.color.epiWhite)  // epiWhite cuando no está marcado
        )

        // Crear un ColorStateList que asocia los colores a los estados
        val colorStateList = ColorStateList(states, colors)

        // Asignar el ColorStateList al CheckBox
        permission1CheckBox.buttonTintList = colorStateList
        permission2CheckBox.buttonTintList = colorStateList
        permission3CheckBox.buttonTintList = colorStateList
        permission4CheckBox.buttonTintList = colorStateList

        shareProfileButton.setOnClickListener {
            Toast.makeText(this, "¡Perfil compartido!", Toast.LENGTH_SHORT).show()
            //TODO: Share profile

            // Set everything to default
            permission1CheckBox.isChecked = false
            permission2CheckBox.isChecked = false
            permission3CheckBox.isChecked = false
            permission4CheckBox.isChecked = false
            shareEmailEditText.text = ""
        }

        // Manage Permissions
        val managePermissionsSwitch: SwitchCompat = findViewById(R.id.managePermissionsSwitch)
        val noPermissionsWarningText: TextView = findViewById(R.id.noPermissionsWarningText)
        val managePermissionsRecyclerView: RecyclerView = findViewById(R.id.managePermissionsRecyclerView)
        managePermissionsSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                noPermissionsWarningText.visibility = View.VISIBLE
                managePermissionsRecyclerView.visibility = View.VISIBLE
            } else {
                noPermissionsWarningText.visibility = View.GONE
                managePermissionsRecyclerView.visibility = View.GONE
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