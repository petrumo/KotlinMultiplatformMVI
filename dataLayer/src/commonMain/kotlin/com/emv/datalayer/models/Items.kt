package com.emv.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Items (

    @SerialName("title")
    val title : String,

    @SerialName("link")
    val link : String,

    @SerialName("media")
    val media : Media,

    @SerialName("date_taken")
    val date_taken : String,

    @SerialName("description")
    val description : String,

    @SerialName("published")
    val published : String,

    @SerialName("author")
    val author : String,

    @SerialName("author_id")
    val author_id : String,

    @SerialName("tags")
    val tags : String
)