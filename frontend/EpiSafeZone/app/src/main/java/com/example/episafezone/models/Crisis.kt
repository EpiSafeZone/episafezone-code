package com.example.episafezone.models

import java.util.Date

class Crisis (
    val id : Int,
    val duration : Int,
    val date : Date,
    val hour : String,
    val context : String,
    val emergency : Boolean,
    val manifestation : String,
    val patient : Int
){}