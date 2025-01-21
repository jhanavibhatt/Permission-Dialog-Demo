package com.example.cursoredittextdemo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val countrySpinner: Spinner = findViewById(R.id.countrySpinner)
        val phoneCodeEditText: EditText = findViewById(R.id.phoneCodeEditText)
        // List of countries and corresponding phone codes
        val countries = listOf("Canada", "USA", "UK", "India", "Japan", "New Zealand")
        val countryPhoneCodes = mapOf(
            "Canada" to "+1",
            "USA" to "+1",
            "UK" to "+44",
            "India" to "+91",
            "Japan" to "+81",
            "New Zealand" to "+64"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countrySpinner.adapter = adapter

        // Set default country (USA)
        val defaultCountry = "USA"
        countrySpinner.setSelection(countries.indexOf(defaultCountry))
        phoneCodeEditText.setText(countryPhoneCodes[defaultCountry])

        // Handle spinner item selection
        countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                val selectedCountry = parent.getItemAtPosition(position).toString()
                val phoneCode = countryPhoneCodes[selectedCountry]
                if (phoneCodeEditText.text.toString() != phoneCode) {
                    phoneCodeEditText.setText(phoneCode)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Handle manual edits in the phone code EditText
        phoneCodeEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val enteredCode = s.toString()
                val matchingCountry = countryPhoneCodes.entries.find { it.value == enteredCode }?.key
                if (matchingCountry != null) {
                    val position = countries.indexOf(matchingCountry)
                    if (countrySpinner.selectedItemPosition != position) {
                        countrySpinner.setSelection(position)
                    }
                }
            }
        })
    }
}