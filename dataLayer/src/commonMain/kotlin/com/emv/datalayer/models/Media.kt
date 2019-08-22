package com.emv.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Media (

    @SerialName("m")
    val m : String
)