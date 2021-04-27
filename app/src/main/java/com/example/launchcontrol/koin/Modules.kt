package com.example.launchcontrol.koin

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.launchcontrol.isswiki.iss_sections.science.data.ScienceRepository
import com.example.launchcontrol.spacex.data.spacex_retrofit.services.LaunchesService
import com.example.launchcontrol.spacex.data.LaunchesRepository
import com.example.launchcontrol.spacex.ui.presenter.ISpaceXContract
import com.example.launchcontrol.spacex.ui.presenter.SpaceXPresenter
import com.example.launchcontrol.generals.utils.JsonReader
import com.example.launchcontrol.generals.utils.SharedPreferencesReduced
import com.example.launchcontrol.isswiki.iss_sections.science.ui.presenter.ScienceViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Modules {
    val modules: Module = module {
        factory {
            SharedPreferencesReduced(androidContext())
        }

        factory {
            JsonReader(androidContext())
        }

        factory {
            ScienceRepository(get())
        }

        factory {
            LaunchesRepository(get(), get())
        }

        factory {(activity: ISpaceXContract.ISpaceXActivity) ->
            SpaceXPresenter(activity, get()) as ISpaceXContract.ISpaceXPresenter
        }

        single {
            Retrofit.Builder()
                .baseUrl("https://api.spacexdata.com/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        factory {
            get<Retrofit>().create(LaunchesService::class.java) as LaunchesService
        }

        viewModel {(fragment: Fragment) ->
            ViewModelProvider(fragment).get(ScienceViewModel::class.java)
        }

    }
}
