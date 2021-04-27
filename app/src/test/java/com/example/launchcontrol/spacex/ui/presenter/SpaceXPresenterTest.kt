package com.example.launchcontrol.spacex.ui.presenter

import com.example.launchcontrol.spacex.data.LaunchesRepository
import com.example.launchcontrol.spacex.domain.entities.*
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import junit.framework.TestCase
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpaceXPresenterTest : TestCase() {
    @MockK
    lateinit var activityMock: ISpaceXContract.ISpaceXActivity
    @MockK
    lateinit var repositoryMock: LaunchesRepository

    lateinit var presenter: SpaceXPresenter

    override fun setUp() {
        super.setUp()
        MockKAnnotations.init(this, relaxUnitFun = true)

        presenter = SpaceXPresenter(activityMock, repositoryMock)
    }

    @Test
    fun testIcaro() {
        val callback = slot<Callback<List<Launches>>>()
        val listLaunch = listOf(Launches(
            "123",
            "",
            Rocket("", SecondStage(listOf())),
            LaunchSite(""),
            Links("", "")
        ))

        val response = Response.success(
            listLaunch
        )

        every {
            repositoryMock.getLaunches(capture(callback), any())
        } answers {
            callback.captured.onResponse(null, response)
        }

        presenter.launchYearListener("2012")

        verify { activityMock.renderRecycler(listLaunch) }
    }
}