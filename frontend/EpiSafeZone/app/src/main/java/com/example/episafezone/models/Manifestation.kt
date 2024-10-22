package com.example.episafezone.models

class Manifestation (var id: Int,
                     var name: String,
                     var description: String,
                     var procedure: String){

    constructor(name: String,
                description: String,
                procedure: String) : this(
        name = name,
        description = description,
        procedure = procedure,
        id = -1
    )
}