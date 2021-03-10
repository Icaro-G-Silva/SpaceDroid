package com.example.launchcontrol.retrofit.entities

import com.google.gson.annotations.SerializedName

data class Launches (
    @SerializedName("mission_name")
    val missionName: String,

    @SerializedName("rocket")
    val rocket: Rocket,

    @SerializedName("launch_site")
    val launchSite: LaunchSite,

    @SerializedName("links")
    val links: Links
)
