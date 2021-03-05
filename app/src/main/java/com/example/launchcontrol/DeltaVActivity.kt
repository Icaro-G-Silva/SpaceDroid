package com.example.launchcontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import kotlin.math.ln

class DeltaVActivity : AppCompatActivity() {

    private lateinit var totalMass: EditText
    private lateinit var dryMass: EditText
    private lateinit var isp: EditText
    private lateinit var gravity: EditText
    private lateinit var userLog: TextView
    private var userMessage: String = "Please, fill all the fields below"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delta_v)

        userLog = findViewById(R.id.user_log)
        displayMessage()

        totalMass = findViewById(R.id.total_mass)
        dryMass = findViewById(R.id.dry_mass)
        isp = findViewById(R.id.isp)
        gravity = findViewById(R.id.gravity)

        generateTextChangedListeners(totalMass)
        generateTextChangedListeners(dryMass)
        generateTextChangedListeners(isp)
        generateTextChangedListeners(gravity)
    }

    private fun generateTextChangedListeners(view: EditText) {
        view.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculate()
                displayMessage()
            }
        })
    }

    private fun calculate() {
        if(totalMass.text.toString().isNotEmpty() && dryMass.text.toString().isNotEmpty() && isp.text.toString().isNotEmpty() && gravity.text.toString().isNotEmpty()) {
            val totalMass = totalMass.text.toString().toDouble()
            val dryMass = dryMass.text.toString().toDouble()
            val isp = isp.text.toString().toDouble()
            val gravity = gravity.text.toString().toDouble()

            if(totalMass <= 0.0 || dryMass <= 0.0 || isp <= 0.0 || gravity <= 0.0) userMessage = "Any field can be zero"
            else {
                val deltaV = ln(totalMass / dryMass) * isp * gravity
                userMessage = if(deltaV < 0) "'Total Mass' can't be less than 'Dry Mass'" else "ΔV = $deltaV"
            }
        } else userMessage = "Please, fill all the fields below"
    }

    private fun displayMessage() {
        userLog.text = userMessage
    }

}