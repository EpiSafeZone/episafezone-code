package com.example.episafezone.businesslogic

import android.widget.Toast
import com.example.episafezone.ActivityProfile
import com.example.episafezone.models.Medication
import com.example.episafezone.models.Manifestation
import com.example.episafezone.models.Patient
import com.example.episafezone.network.ManifestationPetitions
import com.example.episafezone.network.ProfilePetitions
import java.time.LocalDate
import java.util.Date


object ProfileLogic {

    fun getMedicationInfo() : List<Medication>{
        val list = ArrayList<Medication>();
        list.add(Medication("Dalsi",12,"ml",false));
        list.add(Medication("Paracetamol",1,"gr",false));
        list.add(Medication("Ibuprofreno",12,"ml",false));
        list.add(Medication("Antibiótico",12,"ml",false));
        return list;
    }

    fun getManifestInfo() {
        // TODO: Cuando este listo descomentar el codigo siguiente y quitar el resto:
        //ManifestationPetitions.getManifestations(patient)
        responseGetManifesInfo(true, emptyList<Manifestation>())
    }

    fun responseGetManifesInfo(success: Boolean,  list: List<Manifestation>){
        // TODO: Cuando este listo descomentar el codigo siguiente y quitar el resto:
        //if(success){
        //    Toast.makeText(ActivityProfile.getContext(), "Información de manifestaciones obtenida correctamente!", Toast.LENGTH_SHORT).show()
        //    return list
        //} else {
        //    Toast.makeText(ActivityProfile.getContext(), "Error al obtener las manifestaciones del paciente. ", Toast.LENGTH_SHORT).show()
        //    ActivityProfile.updateListOfManifestations(emptyList<Manifestation>())
        //}
        val listTemp = ArrayList<Manifestation>();
        listTemp.add(Manifestation("Yow","Yow","Yow"));
        listTemp.add(Manifestation("Yow","Yow","Yow"));
        listTemp.add(Manifestation("Yow","Yow","Yow"));
        listTemp.add(Manifestation("Yow","Yow","Yow"));
        ActivityProfile.updateListOfManifestations(listTemp)
    }
}