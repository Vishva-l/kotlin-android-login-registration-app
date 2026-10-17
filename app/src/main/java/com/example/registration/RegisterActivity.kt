package com.example.registration

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity: AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        databaseHelper = DatabaseHelper(this)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnGoLogin = findViewById<Button>(R.id.btnGoLogin)

        btnRegister.setOnClickListener {

            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            // Validation

            if (name.isEmpty()) {

                etName.error = "Enter name"
                etName.requestFocus()
                return@setOnClickListener
            }

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

            if (password.length < 6) {

                etPassword.error = "Password must contain at least 6 characters"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            if (confirmPassword.isEmpty()) {

                etConfirmPassword.error = "Confirm password"
                etConfirmPassword.requestFocus()
                return@setOnClickListener
            }

            if (password != confirmPassword) {

                etConfirmPassword.error = "Passwords do not match"
                etConfirmPassword.requestFocus()
                return@setOnClickListener
            }

            // Check existing email

            if (databaseHelper.checkEmail(email)) {

                Toast.makeText(this, "Email already registered", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Insert user

            val result = databaseHelper.registerUser(name, email, password)

            if (result) {

                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()

                // Redirect to Login

                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()

            } else {

                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
            }
        }

        // Go to Login

        btnGoLogin.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}