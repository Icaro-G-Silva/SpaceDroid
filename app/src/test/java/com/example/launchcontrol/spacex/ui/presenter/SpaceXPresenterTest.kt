package com.example.launchcontrol.spacex.ui.presenter

import com.example.launchcontrol.R
import com.example.launchcontrol.spacex.data.LaunchesRepository
import com.example.launchcontrol.spacex.domain.entities.*
import com.example.launchcontrol.spacex.ui.SpaceXActivity
import io.mockk.*
import io.mockk.impl.annotations.MockK
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

    lateinit var listLaunchesMock: List<Launches>

    override fun setUp() {
        super.setUp()
        MockKAnnotations.init(this, relaxUnitFun = true)

        presenter = SpaceXPresenter(activityMock, repositoryMock)

        listLaunchesMock = listOf(Launches(
                "123",
                "",
                Rocket("", SecondStage(listOf())),
                LaunchSite(""),
                Links("", "")
        ))
    }

    @Test
    fun testAPIWhenResponseIsOk() {
        val callback = slot<Callback<List<Launches>>>()

        val response = Response.success(
            listLaunchesMock
        )

        every {
            repositoryMock.getLaunches(capture(callback), any())
        } answers {
            callback.captured.onResponse(null, response)
        }

        presenter.launchYearListener("2012")

        verify { activityMock.renderRecycler(listLaunchesMock) }
    }

    @Test
    fun testAPIWhenResponseIsBad() {
        val callback = slot<Callback<List<Launches>>>()

        every {
            repositoryMock.getLaunches(capture(callback), any())
        } answers {
            callback.captured.onFailure(null, null)
        }

        presenter.launchYearListener("2020")

        verify {
            activityMock.setLoadingInvisible()
            activityMock.showDialog(R.string.api_generics_error_message, R.string.api_generics_error_title)
        }
    }

    @Test
    fun testInputWhenMalformatedYear() {
        presenter.launchYearListener("2000")

        verify { activityMock.showDialog(R.string.mismatch_year_error_message, R.string.mismatch_year_error_title) }
        confirmVerified(activityMock)
    }

    @Test
    fun testWhenPresenterDestroyRelationWithActivity() {
        presenter.destroy()
        assertEquals(null, presenter.activity)
    }

}