package com.example.launchcontrol.deltav.domain

import kotlin.math.ln

class DeltaVUseCase {
    fun calculateDeltaV(totalMass: Double, dryMass: Double, isp: Double, gravity: Double): DeltaVReturn {
        if(totalMass <= 0.0 || dryMass <= 0.0 || isp <= 0.0 || gravity <= 0.0) {
            return DeltaVReturn.ErrorZero()
        }
        else {
            val deltaV = ln(totalMass / dryMass) * isp * gravity
            return if(deltaV < 0) DeltaVReturn.ErrorLessThanZero() else DeltaVReturn.Success(deltaV)
        }
    }
}

sealed class DeltaVReturn {
    class ErrorZero(): DeltaVReturn()
    class ErrorLessThanZero(): DeltaVReturn()
    class Success(val value: Double): DeltaVReturn()
}
