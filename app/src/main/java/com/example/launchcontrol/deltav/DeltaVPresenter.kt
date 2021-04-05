package com.example.launchcontrol.deltav

import com.example.launchcontrol.R
import kotlin.math.ln

class DeltaVPresenter(private val activity: IDeltaVContract.IDeltaVActivity): IDeltaVContract.IDeltaVPresenter {

    override fun initialize() {
        activity.displayMessage(R.string.message_default)
    }

    override fun calculate(totalMass: String,
                           dryMass: String,
                           isp: String,
                           gravity: String) {
        if(totalMass.isNotEmpty() && dryMass.isNotEmpty() && isp.isNotEmpty() && gravity.isNotEmpty()) {
            val totalMass = totalMass.toDouble()
            val dryMass = dryMass.toDouble()
            val isp = isp.toDouble()
            val gravity = gravity.toDouble()

            if(totalMass <= 0.0 || dryMass <= 0.0 || isp <= 0.0 || gravity <= 0.0) {
                activity.displayMessage(R.string.message_zero)
            }
            else {
                val deltaV = ln(totalMass / dryMass) * isp * gravity
                if(deltaV < 0) activity.displayMessage(R.string.message_less_than_zero) else activity.displayDeltaVMessage(R.string.message_deltav, deltaV.toString())
            }
        } else {
            activity.displayMessage(R.string.message_empty_field)
        }
    }
}