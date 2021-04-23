package com.example.launchcontrol.isswiki.iss_sections.science.domain.entities

import com.google.gson.annotations.SerializedName

data class ISSitem(
        @SerializedName("title")
        val title: String,

        @SerializedName("items")
        val items: List<ISSitemContent>
)
