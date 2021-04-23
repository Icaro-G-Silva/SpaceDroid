package com.example.launchcontrol.spacex.domain.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Payload (
        @SerializedName("payload_type")
        val payloadType: String,

        @SerializedName("payload_id")
        val payloadId: String,

        @SerializedName("orbit")
        val orbit: String
): Serializable