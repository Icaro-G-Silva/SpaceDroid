package com.example.launchcontrol.spacex.presenter

import com.example.launchcontrol.retrofit.entities.Launches

interface ISpaceXContract {
    interface ISpaceXPresenter {
        fun launchYearListener(launchYearText: String)
        fun getAPIinfo()
        fun toggleRequestVisibility()
        fun renderRecyclerTrigger()
    }
    interface ISpaceXActivity {
        fun setContentVisible()
        fun setContentInvisible()
        fun setLoadingInvisible()
        fun showDialog(message: Int, title: Int)
        fun renderRecycler(launches: List<Launches>)
    }
}