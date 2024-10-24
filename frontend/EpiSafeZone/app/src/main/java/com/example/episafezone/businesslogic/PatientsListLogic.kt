package com.example.episafezone.businesslogic

import com.example.episafezone.models.Patient
import com.example.episafezone.network.PatientsListPetitions
import java.util.Date

object PatientsListLogic {



    fun getProfileInfo(patient : Patient) {
        PatientsListPetitions.getProfileInfo(patient);
    }
    fun getPatientListInfo() : List<Patient> {
        val list = ArrayList<Patient>()
        list.add(Patient(1, "Onofre", "Bustos", 180, 70, Date(), 21, "blue"))
        list.add(Patient(2, "Cesar", "Gimeno", 130, 60, Date(), 21, "red"))
        return list;
    }
}