package com.example.launchcontrol.twr.ui.presenter

interface ITwrContract {
    interface ITwrPresenter {
        fun initialize()
        fun updateQuantity(operation: Int)
        fun calculate(totalMass: String, gravity: String, thrust: String)
    }

    interface ITwrActivity {
        fun displayMessage(text: Int)
        fun displayTwr(text: Int, twr: String, appendedText: String)
        fun updateQuantity(quantityValue: String)
    }
}