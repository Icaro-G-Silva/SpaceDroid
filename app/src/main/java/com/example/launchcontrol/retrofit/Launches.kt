package com.example.launchcontrol.retrofit

import com.google.gson.annotations.SerializedName

class Launches {
    @SerializedName("mission_name")
    var missionName: String = ""

    @SerializedName("rocket_name")
    var rocketName: String = ""

    @SerializedName("payload_type")
    var payloadType: String = ""

    @SerializedName("payload_id")
    var payloadId: String = ""

    @SerializedName("orbit")
    var orbit: String = ""

    @SerializedName("site_name")
    var siteName: String = ""

    @SerializedName("video_link")
    var videoLink: String = ""
}