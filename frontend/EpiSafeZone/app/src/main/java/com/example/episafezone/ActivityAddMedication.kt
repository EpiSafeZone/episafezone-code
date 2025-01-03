package com.example.episafezone

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.episafezone.businesslogic.MedicationLogic
import com.example.episafezone.databinding.ActivityAddMedicationBinding
import com.example.episafezone.databinding.FragmentProfileBinding
import com.example.episafezone.models.Patient

class ActivityAddMedication : AppCompatActivity() {

    lateinit var binding : ActivityAddMedicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMedicationBinding.inflate(layoutInflater);
        setContentView(binding.root)
        val patient = intent.getSerializableExtra("patient") as Patient
        contextObj = this

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
                MedicationLogic.createMedication(name,dosis,unit,alarm,times,nextAlarm,patient);
            }catch(e : Exception){
                binding.errorText.text=e.message.toString();
            }
        }
    }
    companion object{
        private lateinit var contextObj: Context
        private lateinit var binding: FragmentProfileBinding

        fun getContext() : Context {
            return contextObj
        }
    }
}