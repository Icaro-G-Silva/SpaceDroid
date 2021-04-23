package com.example.launchcontrol.spacex.domain.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Links (
        @SerializedName("mission_patch_small")
        val missionPatch: String?,

        @SerializedName("video_link")
        val videoLink: String?
): Serializable