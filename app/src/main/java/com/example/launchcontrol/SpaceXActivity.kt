package com.example.launchcontrol

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.launchcontrol.retrofit.Launches
import com.example.launchcontrol.retrofit.RetroFitConfig
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.typeOf

class SpaceXActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var loading: ProgressBar

    private lateinit var missionName: TextView
    private lateinit var missionNameContent: TextView

    private lateinit var rocketName: TextView
    private lateinit var rocketNameContent: TextView

    private lateinit var payloadType: TextView
    private lateinit var payloadTypeContent: TextView

    private lateinit var payloadId: TextView
    private lateinit var payloadIdContent: TextView

    private lateinit var orbit: TextView
    private lateinit var orbitContent: TextView

    private lateinit var siteName: TextView
    private lateinit var siteNameContent: TextView

    private lateinit var videoLink: Button
    private lateinit var videoLinkContent: String

    private val launches: MutableList<Launches> = ArrayList()
    private val spinnerList: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_x)

        missionName = findViewById(R.id.missionName)
        missionNameContent = findViewById(R.id.missionName_Content)
        rocketName = findViewById(R.id.rocketName)
        rocketNameContent = findViewById(R.id.rocketName_Content)
        payloadType = findViewById(R.id.payloadType)
        payloadTypeContent = findViewById(R.id.payloadType_Content)
        payloadId = findViewById(R.id.payloadId)
        payloadIdContent = findViewById(R.id.payloadId_Content)
        orbit = findViewById(R.id.orbit)
        orbitContent = findViewById(R.id.orbit_Content)
        siteName = findViewById(R.id.siteName)
        siteNameContent = findViewById(R.id.siteName_Content)

        videoLink = findViewById(R.id.videoLinkButton)
        videoLinkContent = "https://soundcloud.com/icaro-g-silva"

        loading = findViewById(R.id.loading)
        spinner = findViewById(R.id.launches_spinner)
        spinnerList.add("Select an option")

        hideElements()
        getAPIinfo()
    }

    private fun generateSpinnerList() {
        launches.forEach { launch -> spinnerList.add(launch.missionName) }
        val adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerList)
        spinner.adapter = adapter
    }

    private fun generateSpinnerListener() {
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) { hideElements() }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position != 0) renderLaunch(launches[position-1]) else hideElements()
            }
        }
    }

    private fun generateLinkButtonListener() {
        videoLink.setOnClickListener {
            val videoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoLinkContent))
            startActivity(videoIntent)
        }
    }

    private fun getAPIinfo() {
        loading.visibility = View.VISIBLE
        spinner.visibility = View.GONE
        val call: Call<List<Launches>> = RetroFitConfig().getLaunchesService().getLaunches("2020")
        call.enqueue(object: Callback<List<Launches>> {
            override fun onResponse(call: Call<List<Launches>>?, response: Response<List<Launches>>?) {
                if(response?.isSuccessful!!) {
                    loading.visibility = View.GONE
                    spinner.visibility = View.VISIBLE
                    response.body().forEach { launch -> launches.add(launch) }
                    generateSpinnerList()
                    generateSpinnerListener()
                }
            }

            override fun onFailure(call: Call<List<Launches>>?, t: Throwable?) {
                loading.visibility = View.GONE
                generateDialog("You may try again later :)", "Error")
                Log.e(null, t.toString())
            }
        })
    }

    private fun showElements() {
        missionName.visibility = View.VISIBLE
        missionNameContent.visibility = View.VISIBLE
        rocketName.visibility = View.VISIBLE
        rocketNameContent.visibility = View.VISIBLE
        payloadType.visibility = View.VISIBLE
        payloadTypeContent.visibility = View.VISIBLE
        payloadId.visibility = View.VISIBLE
        payloadIdContent.visibility = View.VISIBLE
        orbit.visibility = View.VISIBLE
        orbitContent.visibility = View.VISIBLE
        siteName.visibility = View.VISIBLE
        siteNameContent.visibility = View.VISIBLE
        videoLink.visibility = View.VISIBLE
    }

    private fun hideElements() {
        missionName.visibility = View.GONE
        missionNameContent.visibility = View.GONE
        rocketName.visibility = View.GONE
        rocketNameContent.visibility = View.GONE
        payloadType.visibility = View.GONE
        payloadTypeContent.visibility = View.GONE
        payloadId.visibility = View.GONE
        payloadIdContent.visibility = View.GONE
        orbit.visibility = View.GONE
        orbitContent.visibility = View.GONE
        siteName.visibility = View.GONE
        siteNameContent.visibility = View.GONE
        videoLink.visibility = View.GONE
    }

    private fun renderLaunch(launch: Launches) {
        showElements()
        missionNameContent.text = launch.missionName
        rocketNameContent.text = launch.rocketName
        payloadTypeContent.text = launch.payloadType
        payloadIdContent.text = launch.payloadId
        orbitContent.text = launch.orbit
        siteNameContent.text = launch.siteName
//        videoLinkContent = launch.videoLink
        generateLinkButtonListener()
    }

    private fun generateDialog(message: String, title: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)

        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.ok, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}