package com.example.launchcontrol.deltav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import com.example.launchcontrol.R

class DeltaVActivity : AppCompatActivity(), IDeltaVContract.IDeltaVActivity {

    private lateinit var totalMass: EditText
    private lateinit var dryMass: EditText
    private lateinit var isp: EditText
    private lateinit var gravity: EditText
    private lateinit var userLog: TextView
    var presenter: IDeltaVContract.IDeltaVPresenter? = DeltaVPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_delta_v)

        userLog = findViewById(R.id.user_log)
        totalMass = findViewById(R.id.total_mass)
        dryMass = findViewById(R.id.dry_mass)
        isp = findViewById(R.id.isp)
        gravity = findViewById(R.id.gravity)

        presenter?.initialize()

        generateTextChangedListeners(totalMass)
        generateTextChangedListeners(dryMass)
        generateTextChangedListeners(isp)
        generateTextChangedListeners(gravity)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter = null
    }

    private fun generateTextChangedListeners(view: EditText) {
        view.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter?.calculate(
                    totalMass.text.toString(),
                    dryMass.text.toString(),
                    isp.text.toString(),
                    gravity.text.toString()
                )
            }
        })
    }

    override fun displayMessage(text: Int) {
        userLog.text = getString(text)
    }

    override fun displayDeltaVMessage(text: Int, deltaV: String) {
        userLog.text = getString(text, deltaV)
    }
}