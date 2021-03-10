package com.example.launchcontrol.retrofit.entities

import com.google.gson.annotations.SerializedName

data class SecondStage (
        @SerializedName("payloads")
        val payloads: List<Payload>
)