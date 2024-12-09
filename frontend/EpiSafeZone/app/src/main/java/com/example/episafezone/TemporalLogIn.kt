package com.example.episafezone

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.episafezone.databinding.ActivityTemporalLogInBinding
import com.example.episafezone.models.User

class TemporalLogIn : AppCompatActivity() {

    private lateinit var binding: ActivityTemporalLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemporalLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            User.setId(1)
            User.setName("Mario")
            User.setSurname("Garcia")
            User.setUsername("MG")
            User.setEmail("a@a")
            User.setPassword("1")
            User.setNotifications(1)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            User.setId(2)
            User.setName("Luisa")
            User.setSurname("Garcia")
            User.setUsername("Lu")
            User.setEmail("b@b")
            User.setPassword("luisa")
            User.setNotifications(1)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.button3.setOnClickListener {
            User.setId(3)
            User.setName("Antonio")
            User.setSurname("Pardo")
            User.setUsername("Antopato")
            User.setEmail("Antopato@gmail.com")
            User.setPassword("Antopato")
            User.setNotifications(0)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}