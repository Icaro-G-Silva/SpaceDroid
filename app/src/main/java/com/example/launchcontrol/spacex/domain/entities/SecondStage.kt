package com.example.launchcontrol.spacex.domain.entities

import com.example.launchcontrol.spacex.domain.entities.Payload
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SecondStage (
        @SerializedName("payloads")
        val payloads: List<Payload>
): Serializable