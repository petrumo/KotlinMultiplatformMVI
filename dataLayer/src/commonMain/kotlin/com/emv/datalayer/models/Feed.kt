package com.emv.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Feed (

    @SerialName("title")
    val title : String,

    @SerialName("link")
    val link : String,

    @SerialName("description")
    val description : String,

    @SerialName("modified")
    val modified : String,

    @SerialName("generator")
    val generator : String,

    @SerialName("m")
    val items : List<Items>
) : BaseModel()