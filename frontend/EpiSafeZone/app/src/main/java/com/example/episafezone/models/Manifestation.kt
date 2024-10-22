package com.example.episafezone.models

import java.io.Serializable

class Manifestation (var name: String,
                     var description: String,
                     var procedure: String) {

    private var id: Int = -1

    fun setId(id: Int){
        this.id = id
    }

    fun getId(): Int{
        return this.id
    }

}