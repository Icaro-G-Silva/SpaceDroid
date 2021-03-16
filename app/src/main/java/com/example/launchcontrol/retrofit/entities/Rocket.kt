package com.example.launchcontrol.retrofit.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rocket (
        @SerializedName("rocket_name")
        val rocketName: String,

        @SerializedName("second_stage")
        val secondStage: SecondStage
): Serializable