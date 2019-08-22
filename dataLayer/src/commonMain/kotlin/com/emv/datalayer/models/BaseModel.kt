package com.emv.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
open class BaseModel {

    @SerialName("id")
    val id: Int = 0
}