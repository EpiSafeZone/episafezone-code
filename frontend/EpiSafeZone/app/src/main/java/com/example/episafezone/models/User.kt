package com.example.episafezone.models

object User {
    private var id : Int = -1
    private var name : String = ""
    private var surname : String = ""
    private var username : String = ""
    private var email : String = ""
    private var password : String = ""
    private var notifications : Int = -1

    fun setId(id : Int){
        this.id = id
    }

    fun setName(name : String){
        this.name = name
    }

    fun setSurname(surname : String){
        this.surname = surname
    }

    fun setUsername(username : String){
        this.username = username
    }

    fun setEmail(email : String){
        this.email = email
    }

    fun setPassword(password : String){
        this.password = password
    }

    fun setNotifications(notifications : Int){
        this.notifications = notifications
    }

    fun getId() : Int{
        return id
    }

    fun getName() : String{
        return name
    }

    fun getSurname() : String{
        return surname
    }

    fun getUsername() : String{
        return username
    }

    fun getEmail() : String{
        return email
    }

    fun getPassword() : String{
        return password
    }

    fun getNotifications() : Int{
        return notifications
    }
}