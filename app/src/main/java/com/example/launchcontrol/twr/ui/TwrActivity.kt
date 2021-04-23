package com.example.launchcontrol.twr.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.launchcontrol.R
import com.example.launchcontrol.twr.domain.enums.TwrEnum
import com.example.launchcontrol.twr.ui.presenter.ITwrContract
import com.example.launchcontrol.twr.ui.presenter.TwrPresenter

class TwrActivity : AppCompatActivity(), ITwrContract.ITwrActivity {
    private lateinit var totalMass: EditText
    private lateinit var gravity: EditText
    private lateinit var thrust: EditText
    private lateinit var quantity: TextView
    private lateinit var userLog: TextView

    private lateinit var decrementButton: Button
    private lateinit var incrementButton: Button

    private var presenter: ITwrContract.ITwrPresenter? = TwrPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_twr)

        userLog = findViewById(R.id.user_log)
        quantity = findViewById(R.id.quantity)
        totalMass = findViewById(R.id.total_mass)
        gravity = findViewById(R.id.gravity)
        thrust = findViewById(R.id.thrust)
        decrementButton = findViewById(R.id.decrement_button)
        incrementButton = findViewById(R.id.increment_button)

        presenter?.initialize()

        generateTextChangedListeners(totalMass)
        generateTextChangedListeners(gravity)
        generateTextChangedListeners(thrust)
        generateClickListener(decrementButton, TwrEnum.DECREMENT.representationalNumber)
        generateClickListener(incrementButton, TwrEnum.INCREMENT.representationalNumber)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter = null
    }

    private fun generateTextChangedListeners(view: EditText) {
        view.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter?.calculate(totalMass.text.toString(), gravity.text.toString(), thrust.text.toString())
            }
        })
    }

    private fun generateClickListener(view: View, operation: Int) {
        view.setOnClickListener {
            presenter?.updateQuantity(operation)
            presenter?.calculate(totalMass.text.toString(), gravity.text.toString(), thrust.text.toString())
        }
    }

    override fun displayMessage(text: Int) {
        userLog.text = getString(text)
    }

    @SuppressLint("SetTextI18n")
    override fun displayTwr(text: Int, twr: String, appendedText: String) {
        userLog.text = getString(text, twr) + appendedText
    }

    override fun updateQuantity(quantityValue: String) {
        quantity.text = quantityValue
    }

}