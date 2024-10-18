package com.example.episafezone.businesslogic

import com.example.episafezone.models.Medication

class ProfileLogic {

    fun getProfileInfo() : Int{
        return 1;
    }

    fun getMedicationInfo() : List<Medication>{
        val list = ArrayList<Medication>();
        list.add(Medication("Dalsi",12,"ml",false));
        list.add(Medication("Paracetamol",1,"gr",false));
        list.add(Medication("Ibuprofreno",12,"ml",false));
        list.add(Medication("Antibiótico",12,"ml",false));
        return list;
    }

    fun getManifestInfo() : List<String>{
        val list = ArrayList<String>();
        list.add("Yow");
        list.add("Yow");
        list.add("Yow");
        list.add("Yow");
        return list;
    }
}