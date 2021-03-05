package com.example.launchcontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class TwrActivity : AppCompatActivity() {
    private lateinit var totalMass: EditText
    private lateinit var gravity: EditText
    private lateinit var thrust: EditText
    private lateinit var quantity: TextView
    private lateinit var userLog: TextView

    private lateinit var decrementButton: Button
    private lateinit var incrementButton: Button

    private var quantityValue: Int = 1
    private var userMessage: String = "Please, fill all the fields below"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twr)

        userLog = findViewById(R.id.user_log)
        displayMessage()
        quantity = findViewById(R.id.quantity)

        totalMass = findViewById(R.id.total_mass)
        gravity = findViewById(R.id.gravity)
        thrust = findViewById(R.id.thrust)
        generateTextChangedListeners(totalMass)
        generateTextChangedListeners(gravity)
        generateTextChangedListeners(thrust)

        decrementButton = findViewById(R.id.decrement_button)
        incrementButton = findViewById(R.id.increment_button)
        generateClickListener(decrementButton, 0)
        generateClickListener(incrementButton, 1)

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

    private fun generateClickListener(view: View, operation: Int) {
        view.setOnClickListener {
            if(operation == 1) quantityValue++ else if(quantityValue > 1) quantityValue--
            updateQuantity()
            calculate()
            displayMessage()
        }
    }

    private fun calculate() {
        if(totalMass.text.toString().isNotEmpty() && gravity.text.toString().isNotEmpty() && thrust.text.toString().isNotEmpty()) {
            val totalMass = totalMass.text.toString().toDouble()
            val gravity = gravity.text.toString().toDouble()
            val thrust = thrust.text.toString().toDouble()

            if(totalMass <= 0.0 || gravity <= 0.0 || thrust <= 0.0) userMessage = "Any field can be zero"
            else {
                val twr = (thrust * quantityValue) / (totalMass * gravity)
                userMessage = "TWR = $twr"
                userMessage = if(twr > 1) "$userMessage\nLiftoff!" else "$userMessage\nYou need more power!"
            }
        } else userMessage = "Please, fill all the fields below"
    }

    private fun displayMessage() {
        userLog.text = userMessage
    }

    private fun updateQuantity() {
        quantity.text = quantityValue.toString()
    }

}