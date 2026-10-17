package com.example.registration

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val tvWelcome =
            findViewById<TextView>(R.id.tvWelcome)

        val tvEmail =
            findViewById<TextView>(R.id.tvEmail)

        val btnLogout =
            findViewById<Button>(R.id.btnLogout)

        val name =
            intent.getStringExtra("USER_NAME")

        val email =
            intent.getStringExtra("USER_EMAIL")

        tvWelcome.text =
            "Welcome, $name!"

        tvEmail.text =
            "Email: $email"

        btnLogout.setOnClickListener {

            val intent = Intent(
                this,
                LoginActivity::class.java
            )

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

            finish()
        }
    }
}