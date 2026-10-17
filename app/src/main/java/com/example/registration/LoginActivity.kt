package com.example.registration

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        databaseHelper = DatabaseHelper(this)

        val etEmail =
            findViewById<EditText>(R.id.etLoginEmail)

        val etPassword =
            findViewById<EditText>(R.id.etLoginPassword)

        val btnLogin =
            findViewById<Button>(R.id.btnLogin)

        val btnRegister =
            findViewById<Button>(R.id.btnRegister)

        btnLogin.setOnClickListener {

            val email =
                etEmail.text.toString().trim()

            val password =
                etPassword.text.toString().trim()

            if (email.isEmpty()) {

                etEmail.error = "Enter email"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {

                etPassword.error = "Enter password"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            // Check database

            val loginSuccess =
                databaseHelper.loginUser(
                    email,
                    password
                )

            if (loginSuccess) {

                val name =
                    databaseHelper.getUserName(email)

                Toast.makeText(
                    this,
                    "Login successful",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(
                    this,
                    HomeActivity::class.java
                )

                intent.putExtra(
                    "USER_NAME",
                    name
                )

                intent.putExtra(
                    "USER_EMAIL",
                    email
                )

                startActivity(intent)

                finish()

            } else {

                Toast.makeText(
                    this,
                    "Invalid email or password",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        btnRegister.setOnClickListener {

            val intent = Intent(
                this,
                RegisterActivity::class.java
            )

            startActivity(intent)
        }
    }
}