package com.example.launchcontrol.spacex.ui.presenter

import com.example.launchcontrol.retrofit.entities.Launches

interface ISpaceXContract {
    interface ISpaceXPresenter {
        fun launchYearListener(launchYearText: String)
        fun destroy()
    }
    interface ISpaceXActivity {
        fun setContentVisible()
        fun setContentInvisible()
        fun setLoadingInvisible()
        fun showDialog(message: Int, title: Int)
        fun renderRecycler(launches: List<Launches>)
    }
}