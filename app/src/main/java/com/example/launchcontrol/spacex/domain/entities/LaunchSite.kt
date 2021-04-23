package com.example.launchcontrol.spacex.domain.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LaunchSite (
        @SerializedName("site_name")
        val siteName: String
): Serializable