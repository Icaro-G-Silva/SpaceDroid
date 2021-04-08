package com.example.launchcontrol.entities.jsonentities

import com.google.gson.annotations.SerializedName

data class ISSitemContent(
        @SerializedName("item")
        val item: String
)
