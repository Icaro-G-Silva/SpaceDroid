package com.example.launchcontrol.retrofit.entities

import com.google.gson.annotations.SerializedName

data class Links (
        @SerializedName("video_link")
        val videoLink: String?
)