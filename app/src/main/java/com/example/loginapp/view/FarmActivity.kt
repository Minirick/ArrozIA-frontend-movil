package com.example.loginapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R
import android.widget.ArrayAdapter


class FarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farm)

        // Campos del formulario
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val areaEditText = findViewById<EditText>(R.id.areaEditText)
        val saveButton = findViewById<Button>(R.id.saveButton)
        //val mapPlaceholder = findViewById<ImageView>(R.id.mapPlaceholder)

        val unitSpinner: Spinner = findViewById(R.id.unitSpinner)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.units_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitSpinner.adapter = adapter


        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val area = areaEditText.text.toString()

            if (name.isEmpty() || area.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Finca creada con éxito", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
