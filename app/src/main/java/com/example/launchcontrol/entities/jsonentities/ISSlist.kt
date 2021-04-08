package com.example.launchcontrol.entities.jsonentities

import com.google.gson.annotations.SerializedName

data class ISSlist(
        @SerializedName("experiments")
        val experiments: List<ISSitem>
)
