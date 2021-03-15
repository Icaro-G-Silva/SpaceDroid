package com.example.launchcontrol

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import com.example.launchcontrol.enums.SpaceXEnum
import com.example.launchcontrol.retrofit.entities.Launches
import com.example.launchcontrol.retrofit.RetroFitConfig
import com.example.launchcontrol.utils.Dialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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

    private lateinit var launchYear: EditText
    private lateinit var launchYearContent: String

    private val date: Date = Calendar.getInstance().time
    private val actualYear: String = SimpleDateFormat("yyyy", Locale.getDefault()).format(date)

    private val launches: MutableList<Launches> = ArrayList()
    private val spinnerList: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_x)

        launchYear = findViewById(R.id.launchYear)
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
        loading = findViewById(R.id.loading)
        spinner = findViewById(R.id.launches_spinner)

        hideElements()
        generateLaunchYearListener()
    }

    private fun generateLaunchYearListener() {
        launchYear.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val launchYearText: String = launchYear.text.toString()
                if(launchYearText.isNotEmpty() && launchYearText.length == 4) {
                    if(launchYearText.toInt() >= SpaceXEnum.INITIAL_YEAR.year && launchYearText.toInt() < actualYear.toInt()) {
                        launchYearContent = launchYearText
                        getAPIinfo()
                    } else Dialog("We don't have any launches' information for this year\n\nD:", "Mismatch year", this@SpaceXActivity).show()
                }
            }
        })
    }

    private fun generateSpinnerList() {
        spinnerList.clear()
        spinnerList.add("Select an option")
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
        hideElements()
        loading.visibility = View.VISIBLE
        spinner.visibility = View.GONE
        val call: Call<List<Launches>> = RetroFitConfig().getLaunchesService().getLaunches(launchYearContent)
        call.enqueue(object: Callback<List<Launches>> {
            override fun onResponse(call: Call<List<Launches>>?, response: Response<List<Launches>>?) {
                if(response?.isSuccessful!!) {
                    loading.visibility = View.GONE
                    spinner.visibility = View.VISIBLE
                    launches.clear()
                    response.body().forEach { launch -> launches.add(launch) }
                    generateSpinnerList()
                    generateSpinnerListener()
                }
            }

            override fun onFailure(call: Call<List<Launches>>?, t: Throwable?) {
                loading.visibility = View.GONE
                Dialog("You may try again later\n\n:)", "API Error", this@SpaceXActivity).show()
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
        rocketNameContent.text = launch.rocket.rocketName
        payloadTypeContent.text = launch.rocket.secondStage.payloads[0].payloadType
        payloadIdContent.text = launch.rocket.secondStage.payloads[0].payloadId
        orbitContent.text = launch.rocket.secondStage.payloads[0].orbit
        siteNameContent.text = launch.launchSite.siteName

        videoLink.visibility = View.GONE
        launch.links.videoLink?.let { link ->
            videoLinkContent = link
            videoLink.visibility = View.VISIBLE
            generateLinkButtonListener()
        }
    }

}