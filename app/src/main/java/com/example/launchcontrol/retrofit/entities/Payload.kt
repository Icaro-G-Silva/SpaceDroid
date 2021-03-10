package com.example.launchcontrol.retrofit.entities

import com.google.gson.annotations.SerializedName

data class Payload (
        @SerializedName("payload_type")
        val payloadType: String,

        @SerializedName("payload_id")
        val payloadId: String,

        @SerializedName("orbit")
        val orbit: String
)