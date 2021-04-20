@file:Suppress("NAME_SHADOWING")
package com.example.launchcontrol.twr

import com.example.launchcontrol.R
import com.example.launchcontrol.enums.TwrEnum

class TwrPresenter(private val activity: ITwrContract.ITwrActivity): ITwrContract.ITwrPresenter {
    private val twrUseCase = TwrUseCase()
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

            when(val twr = twrUseCase.calculateTwr(totalMass, gravity, thrust, quantityValue)) {
                is TwrReturn.ErrorZero -> activity.displayMessage(R.string.message_zero)
                is TwrReturn.Success -> activity.displayTwr(R.string.message_twr, twr.value.toString(), "\nYou need more power!")
                is TwrReturn.SuccessLiftoff -> activity.displayTwr(R.string.message_twr, twr.value.toString(), "\nLiftoff!")
            }
        } else {
            activity.displayMessage(R.string.message_empty_field)
        }
    }
}