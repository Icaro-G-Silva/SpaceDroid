package com.example.launchcontrol.retrofit.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SecondStage (
        @SerializedName("payloads")
        val payloads: List<Payload>
): Serializable