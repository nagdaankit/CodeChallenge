package com.codingchallenge.ctw.data.entities

import com.google.gson.annotations.SerializedName

data class SourceModel(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)