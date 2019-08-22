package com.emv.udf.middleware

import com.emv.udf.actions.Action
import com.emv.udf.results.DataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

typealias Emit = (Action) -> Unit
abstract class BaseMiddleware<T>(private val emit: Emit) : CoroutineScope {
    private val compositeJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + compositeJob

    private var loadDataJob: Job? = null

    fun loadData() {
        if (loadDataJob?.isActive == true) return
        emit(DataResult.Loading())

        loadDataJob = launch {
            emit(getData())
        }
    }

    abstract suspend fun getData() : DataResult<T>

    private fun emit(dataResult: DataResult<T>) {
        emit(Action.OnData(dataResult))
    }

    fun clear() {
        compositeJob.cancel()
    }
}