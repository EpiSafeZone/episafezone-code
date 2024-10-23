package com.example.episafezone.businesslogic

import com.example.episafezone.models.Medication
import com.example.episafezone.models.Patient
import com.example.episafezone.models.Reminder
import com.example.episafezone.network.MedicationPetitions

object MedicationLogic {

    fun createMedication(name : String, dosis : String, unit : String, alarm:Boolean, times : String?, nextAlarm: String?, patient : Patient):Boolean{
        if(name == ""){throw Exception("Debes rellenar el nombre")}
        if(unit == ""){throw Exception("Debes rellenar la unidad")}
        if(dosis == ""){throw Exception("Debes rellenar la dosis")}
        if(alarm){
            if(nextAlarm == ""){throw Exception("Debes rellenar las horas")}
            if(times == ""){throw Exception("Debes rellenar la cantidad de tomas")}
            Reminder(times!!.toInt(), nextAlarm!!);
        }
        var medication = Medication(0,name,dosis.toInt(),unit,alarm)
        MedicationPetitions.addMedication(medication,patient)
        return true;
    }

    fun editMedication(id:Int,name : String, dosis : String, unit : String,
                       alarm:Boolean, times : String?, nextAlarm: String?,patient: Patient) : Boolean {
        if(name == ""){throw Exception("Debes rellenar el nombre")}
        if(unit == ""){throw Exception("Debes rellenar la unidad")}
        if(dosis == ""){throw Exception("Debes rellenar la dosis")}
        if(alarm){
            if(nextAlarm == ""){throw Exception("Debes rellenar las horas")}
            if(times == ""){throw Exception("Debes rellenar la cantidad de tomas")}
            Reminder(times!!.toInt(), nextAlarm!!);
        }
        val medication = Medication(id,name,dosis.toInt(),unit,alarm)
        MedicationPetitions.editMedication(medication,patient)
        return true;
    }
}