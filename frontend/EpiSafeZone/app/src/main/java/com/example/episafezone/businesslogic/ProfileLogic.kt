package com.example.episafezone.businesslogic

import com.example.episafezone.models.Manifestation

class ProfileLogic {

    fun getProfileInfo() : Int{
        return 1;
    }

    fun getMedicineInfo() : List<String>{
        val list = ArrayList<String>();
        list.add("Yow");
        list.add("Yow");
        list.add("Yow");
        list.add("Yow");
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