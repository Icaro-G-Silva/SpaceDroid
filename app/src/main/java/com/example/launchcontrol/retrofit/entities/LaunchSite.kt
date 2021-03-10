package com.example.launchcontrol.retrofit.entities

import com.google.gson.annotations.SerializedName

data class LaunchSite (
        @SerializedName("site_name")
        val siteName: String
)