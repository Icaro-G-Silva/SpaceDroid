@file:Suppress("NAME_SHADOWING")
package com.example.launchcontrol.deltav.ui.presenter

import com.example.launchcontrol.R
import com.example.launchcontrol.deltav.domain.DeltaVReturn
import com.example.launchcontrol.deltav.domain.DeltaVUseCase

class DeltaVPresenter(private val activity: IDeltaVContract.IDeltaVActivity): IDeltaVContract.IDeltaVPresenter {
    private val deltaVUsecase = DeltaVUseCase()

    override fun initialize() {
        activity.displayMessage(R.string.message_empty_field)
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

            when(val deltaV = deltaVUsecase.calculateDeltaV(totalMass, dryMass, isp, gravity)) {
                is DeltaVReturn.ErrorZero -> activity.displayMessage(R.string.message_zero)
                is DeltaVReturn.ErrorLessThanZero -> activity.displayMessage(R.string.message_less_than_zero)
                is DeltaVReturn.Success -> activity.displayDeltaVMessage(R.string.message_deltav, deltaV.value.toString())
            }
        } else {
            activity.displayMessage(R.string.message_empty_field)
        }
    }
}