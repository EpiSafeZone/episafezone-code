package com.example.episafezone

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.episafezone.businesslogic.MedicationLogic
import com.example.episafezone.databinding.ActivityEditMedicationBinding
import com.example.episafezone.models.Medication
import com.example.episafezone.models.Patient

class ActivityEditMedication : AppCompatActivity() {

    lateinit var binding : ActivityEditMedicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMedicationBinding.inflate(layoutInflater);
        setContentView(binding.root)

        val medication = intent.getSerializableExtra("medication") as? Medication
        val patient = intent.getSerializableExtra("patient") as Patient

        binding.medicationNameInput.setText(medication!!.name)
        binding.medicationAmountInput.setText(medication.dosis.toString())
        binding.medicationUnitInput.setText(medication.unit)
        binding.alarmCheckBox.isChecked = medication.alarm

        binding.alarmCheckBox.setOnCheckedChangeListener{_,isChecked ->
            binding.alarmHoursInput.isEnabled=isChecked;
            binding.alarmsTakesInput.isEnabled=isChecked;
        }

        binding.cancelEditMedicationButt.setOnClickListener{
            finish()
        }

        binding.acceptEditMedicationButt.setOnClickListener{
            val name = binding.medicationNameInput.text.toString();
            val dosis = binding.medicationAmountInput.text.toString();
            val unit = binding.medicationUnitInput.text.toString();
            val alarm = binding.alarmCheckBox.isChecked;
            val times : String?;
            val nextAlarm : String?
            if(alarm){
                times= binding.alarmsTakesInput.text.toString();
                nextAlarm = binding.alarmHoursInput.text.toString();
            }else{
                times = null
                nextAlarm = null
            }

            try{
                MedicationLogic.editMedication(medication.id,name,dosis,unit,alarm,times,nextAlarm,patient);
            }catch(e : Exception){
                binding.errorText.text=e.message.toString();
            }
        }
    }
}