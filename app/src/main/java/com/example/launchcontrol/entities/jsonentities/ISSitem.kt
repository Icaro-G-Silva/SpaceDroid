package com.example.launchcontrol.entities.jsonentities

import com.google.gson.annotations.SerializedName

data class ISSitem(
        @SerializedName("title")
        val title: String,

        @SerializedName("items")
        val items: List<ISSitemContent>
)
