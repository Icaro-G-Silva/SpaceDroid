package com.example.launchcontrol.isswiki.iss_sections.science.domain.entities

import com.example.launchcontrol.isswiki.iss_sections.science.domain.entities.ISSitem
import com.google.gson.annotations.SerializedName

data class ISSlist(
        @SerializedName("experiments")
        val experiments: List<ISSitem>
)
