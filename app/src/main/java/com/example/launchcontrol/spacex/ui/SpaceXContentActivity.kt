package com.example.launchcontrol.spacex.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.launchcontrol.R
import com.example.launchcontrol.spacex.domain.entities.Launches

class SpaceXContentActivity : AppCompatActivity() {
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

    private lateinit var launch: Launches

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space_x_content)

        intent.getSerializableExtra("launch")?.let {
            launch = it as Launches
        }

        supportActionBar?.title = getString(R.string.flight_number, launch.flightNumber)

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

        renderLaunch(launch)
    }

    private fun renderLaunch(launch: Launches) {
        missionNameContent.text = launch.missionName
        rocketNameContent.text = launch.rocket.rocketName
        payloadTypeContent.text = launch.rocket.secondStage.payloads[0].payloadType
        payloadIdContent.text = launch.rocket.secondStage.payloads[0].payloadId
        orbitContent.text = launch.rocket.secondStage.payloads[0].orbit
        siteNameContent.text = launch.launchSite.siteName

        videoLink.visibility = View.GONE
        launch.links.videoLink?.let { link ->
            val videoLinkContent: String = link
            videoLink.visibility = View.VISIBLE
            generateLinkButtonListener(videoLinkContent)
        }
    }

    private fun generateLinkButtonListener(link: String) {
        videoLink.setOnClickListener {
            val videoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(videoIntent)
        }
    }
}