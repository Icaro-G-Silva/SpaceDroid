package com.example.launchcontrol.spacex.data

import com.example.launchcontrol.generals.utils.SharedPreferencesReduced
import com.example.launchcontrol.spacex.data.spacex_retrofit.services.LaunchesService
import com.example.launchcontrol.spacex.domain.entities.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import okhttp3.Request
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaunchesRepositoryTest : TestCase() {

    @MockK
    lateinit var sharedPreferencesMock: SharedPreferencesReduced

    @MockK
    lateinit var serviceMock: LaunchesService

    lateinit var repository: LaunchesRepository

    override fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        repository = LaunchesRepository(sharedPreferencesMock, serviceMock)
        super.setUp()
    }

    @Test
    fun testGetLaunches() {
        every {
            sharedPreferencesMock.getPreference(any(), any<String>())
        } returns "DefaultString"

        val callback = object : Callback<List<Launches>> {
            override fun onResponse(
                call: Call<List<Launches>>?,
                response: Response<List<Launches>>?
            ) {
                assertEquals("123", response?.body()?.get(0)?.flightNumber)
            }

            override fun onFailure(call: Call<List<Launches>>?, t: Throwable?) {
                assertTrue(false)
            }
        }

        val call = object : Call<List<Launches>> {
            override fun clone(): Call<List<Launches>> {
                TODO("Not yet implemented")
            }

            override fun execute(): Response<List<Launches>> {
                return Response.success(
                    listOf(
                        Launches(
                            "123",
                            "",
                            Rocket("", SecondStage(listOf())),
                            LaunchSite(""),
                            Links("", "")
                        )
                    )
                )
            }

            override fun enqueue(callback: Callback<List<Launches>>?) {
                callback?.onResponse(this, execute())
            }

            override fun isExecuted(): Boolean {
                TODO("Not yet implemented")
            }

            override fun cancel() {
                TODO("Not yet implemented")
            }

            override fun isCanceled(): Boolean {
                TODO("Not yet implemented")
            }

            override fun request(): Request {
                TODO("Not yet implemented")
            }

        }

        every {
            serviceMock.getLaunches(any())
        } returns call

        repository.getLaunches(callback, "2012")
    }

    @Test
    fun testLaunchYearPrefWhenNotInPreferences() {
//        val callback = slot<Callback<List<Launches>>>()
//        every {
//            repository.getLaunches(capture(callback), any())
//        } answers {
//            repository.getLaunches(callback.captured, "2012")
//        }
//        repository.getLaunches(callback, "2012")

        val callback = object : Callback<List<Launches>> {
            override fun onResponse(
                    call: Call<List<Launches>>?,
                    response: Response<List<Launches>>?
            ) {
                assertEquals("123", response?.body()?.get(0)?.flightNumber)
            }

            override fun onFailure(call: Call<List<Launches>>?, t: Throwable?) {
                assertTrue(false)
            }
        }

        val call = object : Call<List<Launches>> {
            override fun clone(): Call<List<Launches>> {
                TODO("Not yet implemented")
            }

            override fun execute(): Response<List<Launches>> {
                return Response.success(
                        listOf(
                                Launches(
                                        "123",
                                        "",
                                        Rocket("", SecondStage(listOf())),
                                        LaunchSite(""),
                                        Links("", "")
                                )
                        )
                )
            }

            override fun enqueue(callback: Callback<List<Launches>>?) {
                callback?.onResponse(this, execute())
            }

            override fun isExecuted(): Boolean {
                TODO("Not yet implemented")
            }

            override fun cancel() {
                TODO("Not yet implemented")
            }

            override fun isCanceled(): Boolean {
                TODO("Not yet implemented")
            }

            override fun request(): Request {
                TODO("Not yet implemented")
            }

        }

        every {
            serviceMock.getLaunches(any())
        } returns call

        every {
            sharedPreferencesMock.getPreference(any(), any<String>())
        } returns ""

        repository.getLaunches(callback, "2012")

        verify { sharedPreferencesMock.setPreference(any(), any<String>()) }
    }

}