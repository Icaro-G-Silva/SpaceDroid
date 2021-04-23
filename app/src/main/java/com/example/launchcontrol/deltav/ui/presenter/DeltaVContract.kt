package com.example.launchcontrol.deltav.ui.presenter

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