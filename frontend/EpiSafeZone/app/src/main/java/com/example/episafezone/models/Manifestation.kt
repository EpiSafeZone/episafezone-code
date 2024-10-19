package com.example.episafezone.models

class Manifestation (var name: String,
                     var description: String,
                     var procedure: String){

    private var id: Int = -1

    fun setId(id: Int){
        this.id = id
    }

    fun getId(): Int{
        return this.id
    }

}