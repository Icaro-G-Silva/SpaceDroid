package com.example.launchcontrol.twr

import com.example.launchcontrol.R

class TwrUseCase {
    fun calculateTwr(totalMass: Double, gravity: Double, thrust: Double, quantityValue: Int): TwrReturn {
        return if(totalMass <= 0.0 || gravity <= 0.0 || thrust <= 0.0) TwrReturn.ErrorZero()
        else {
            val twr = (thrust * quantityValue) / (totalMass * gravity)
            if(twr > 1) TwrReturn.SuccessLiftoff(twr)
            else TwrReturn.Success(twr)
        }
    }
}

sealed class TwrReturn {
    class ErrorZero: TwrReturn()
    class Success(val value: Double): TwrReturn()
    class SuccessLiftoff(val value: Double): TwrReturn()
}