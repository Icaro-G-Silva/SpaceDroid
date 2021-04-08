@file:Suppress("NAME_SHADOWING")
package com.example.launchcontrol.twr

import com.example.launchcontrol.R
import com.example.launchcontrol.enums.TwrEnum

class TwrPresenter(private val activity: ITwrContract.ITwrActivity): ITwrContract.ITwrPresenter {

    private var quantityValue: Int = 1

    override fun initialize() {
        activity.displayMessage(R.string.message_empty_field)
    }

    override fun updateQuantity(operation: Int) {
        if(operation == TwrEnum.INCREMENT.representationalNumber) quantityValue++ else if(quantityValue > 1) quantityValue--
        activity.updateQuantity(quantityValue.toString())
    }

    override fun calculate(totalMass: String, gravity: String, thrust: String) {
        if(totalMass.isNotEmpty() && gravity.isNotEmpty() && thrust.isNotEmpty()) {
            val totalMass = totalMass.toDouble()
            val gravity = gravity.toDouble()
            val thrust = thrust.toDouble()

            if(totalMass <= 0.0 || gravity <= 0.0 || thrust <= 0.0) activity.displayMessage(R.string.message_zero)
            else {
                val twr = (thrust * quantityValue) / (totalMass * gravity)
                if(twr > 1) {
                    activity.displayTwr(R.string.message_twr, twr.toString(), "\nLiftoff!")
                }
                else  {
                    activity.displayTwr(R.string.message_twr, twr.toString(), "\nYou need more power!")
                }
            }
        } else {
            activity.displayMessage(R.string.message_empty_field)
        }
    }
}