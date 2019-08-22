package com.emv.udf.actions

import com.emv.udf.results.DataResult

sealed class Action {
    object OnInit : Action()
    object OnHome : Action()
    object OnMesh : Action()
    object OnMap : Action()
    data class OnData<T>(val dataResult: DataResult<T>) : Action()
}

