package com.example.lab03_sample

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.lab03_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val edtName: EditText = binding.edtName
        val edtEmail: EditText = binding.edtEmail
        val edtPhone: EditText = binding.edtPhone
        val edtPassword: EditText = binding.edtPassword
        val edtRePassword: EditText = binding.edtRePassword
        val btnSubmit: Button = binding.btnSubmit
        val btnCancel: Button = binding.btnCancel


    fun showAlertBox(
        context: Context,
        formData: FormData
    ) {

        val builder = AlertDialog.Builder(context)

        val message = "Email: ${formData.email}\n" +
                "Phone: ${formData.phone}\n" +
                "Passwords: ${if (formData.password == formData.rePassword) "Matching" else "Not Matching"}."

        builder.setTitle("Welcome ${formData.name}!")
        builder.setMessage(message)

        builder.setPositiveButton("Ok") { _, _ ->
            Toast.makeText(context, "Submitted", Toast.LENGTH_LONG).show()
        }

        builder.setNegativeButton("Cancel") { _, _ -> }

        val dialog = builder.create()
        dialog.show()
    }
        btnSubmit.setOnClickListener {

            val formData = FormData(
                name = edtName.text.toString().trim(),
                email = edtEmail.text.toString().trim(),
                phone = edtPhone.text.toString().trim(),
                password = edtPassword.text.toString().trim(),
                rePassword = edtRePassword.text.toString().trim()
            )

            when {
                formData.name.isEmpty() -> {
                    Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                }

                formData.email.isEmpty() -> {
                    Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                }

                formData.phone.isEmpty() -> {
                    Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show()
                }

                formData.password.isEmpty() -> {
                    Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
                }

                formData.rePassword.isEmpty() -> {
                    Toast.makeText(this, "Please retype your password", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    showAlertBox(this, formData)
                }
            }
        }

        btnCancel.setOnClickListener {
            edtName.setText("")
            edtEmail.setText("")
            edtPhone.setText("")
            edtPassword.setText("")
            edtRePassword.setText("")
        }
    }

}