package com.example.episafezone.businesslogic

import com.example.episafezone.models.Medication
import com.example.episafezone.models.Manifestation
import com.example.episafezone.models.Patient
import com.example.episafezone.network.ProfilePetitions
import java.time.LocalDate
import java.util.Date


class ProfileLogic {

    fun getMedicationInfo() : List<Medication>{
        val list = ArrayList<Medication>();
        list.add(Medication("Dalsi",12,"ml",false));
        list.add(Medication("Paracetamol",1,"gr",false));
        list.add(Medication("Ibuprofreno",12,"ml",false));
        list.add(Medication("Antibiótico",12,"ml",false));
        return list;
    }

    fun getManifestInfo() : List<Manifestation>{
        val list = ArrayList<Manifestation>();
        list.add(Manifestation("Yow","Yow","Yow"));
        list.add(Manifestation("Yow","Yow","Yow"));
        list.add(Manifestation("Yow","Yow","Yow"));
        list.add(Manifestation("Yow","Yow","Yow"));
        return list;
    }
}