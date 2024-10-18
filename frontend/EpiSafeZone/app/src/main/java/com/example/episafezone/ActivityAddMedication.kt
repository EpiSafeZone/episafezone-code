package com.example.episafezone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.episafezone.businesslogic.MedicationLogic
import com.example.episafezone.databinding.ActivityAddMedicationBinding
import com.example.episafezone.models.Medication
import com.example.episafezone.models.Reminder

class ActivityAddMedication : AppCompatActivity() {

    lateinit var binding : ActivityAddMedicationBinding
    val medicationLogic = MedicationLogic();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMedicationBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.alarmCheckBox.setOnCheckedChangeListener{_,isChecked ->
            binding.alarmHoursInput.isEnabled=isChecked;
            binding.alarmsTakesInput.isEnabled=isChecked;
        }

        binding.cancelMedicationButt.setOnClickListener{
            finish()
        }

        binding.acceptMedicationButt.setOnClickListener{
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
                medicationLogic.createMedication(name,dosis,unit,alarm,times,nextAlarm);
            }catch(e : Exception){
                binding.errorText.text=e.message.toString();
            }
        }
    }
}