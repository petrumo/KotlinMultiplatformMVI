package com.emv.udf.actions

import com.emv.udf.results.DataResult

sealed class Action {
    object Init : Action()
    object Home : Action()
    object Mesh : Action()
    object Map : Action()
    data class OnData<T>(val dataResult: DataResult<T>) : Action()
}

