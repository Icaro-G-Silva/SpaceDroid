package com.example.launchcontrol.deltav

interface IDeltaVContract {
    interface IDeltaVActivity {
        fun displayMessage(text: Int)
        fun displayDeltaVMessage(text: Int, deltaV: String)
    }

    interface IDeltaVPresenter {
        fun initialize()
        fun calculate(
            totalMass: String,
            dryMass: String,
            isp: String,
            gravity: String
        )
    }
}