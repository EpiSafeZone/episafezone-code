package com.example.episafezone

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.episafezone.adapter.ManifestationSpinnerAdapter
import com.example.episafezone.businesslogic.ChronometerLogic
import com.example.episafezone.businesslogic.StartCrisisLogic
import com.example.episafezone.databinding.ActivityRegisterCrisisBinding
import com.example.episafezone.models.Crisis
import com.example.episafezone.models.Manifestation
import com.example.episafezone.network.StartCrisisPetitions
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ActivityRegisterCrisis : AppCompatActivity()  {

    lateinit var manifestationSelected: Manifestation

    private lateinit var binding: ActivityRegisterCrisisBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterCrisisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contextObj = this

        StartCrisisPetitions.initializeQueue()

        val fromChrono = intent.getBooleanExtra("chrono",false)
        if(fromChrono){
            val duration = intent.getLongExtra("time",0)
            binding.duration.setText(duration.toString())
            val date = LocalDate.now()
            binding.date.setText(date.toString())
        }

        binding.date.setOnClickListener{
            showDatePickerDialog()
        }

        val spinnerAdapter = ManifestationSpinnerAdapter(this, ChronometerLogic.manifestations)
        binding.manifestation.adapter = spinnerAdapter

        binding.manifestation.setSelection(0)

        manifestationSelected = binding.manifestation.selectedItem as Manifestation

        binding.manifestation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                manifestationSelected = parent?.getItemAtPosition(position) as Manifestation
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.manifestation.setSelection(0)

                manifestationSelected = binding.manifestation.selectedItem as Manifestation
            }
        }

        binding.time.setOnClickListener {
            showTimePickerDialog()
        }

        binding.acceptCrisisButt.setOnClickListener{
            val hour = binding.time.text.toString()
            var emergency = false
            val duration = binding.duration.text.toString().toInt()
            if (duration >= 300){
                emergency = true
            }
            val dateString = binding.date.text.toString()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateObject: Date = dateFormat.parse(dateString)
            Log.d("Date", dateObject.toString())
            val contextCrisis = binding.context.text.toString()
            val crisis = Crisis(duration,dateObject,hour,contextCrisis,emergency, manifestationSelected.name, manifestationSelected.id, MainActivity.getPatient().id)
            StartCrisisLogic.registerCrisis(crisis)
        }

        binding.cancelCrisisButt.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("load", MainActivity.CALENDAR_VIEW)
            startActivity(intent)
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)

                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)

                binding.date.setText(formattedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                binding.time.setText(formattedTime)
            },
            hour,
            minute,
            true
        )

        timePickerDialog.show()
    }

    companion object{
        lateinit var contextObj: Context

        fun getContext(): Context{
            return contextObj
        }
    }
}