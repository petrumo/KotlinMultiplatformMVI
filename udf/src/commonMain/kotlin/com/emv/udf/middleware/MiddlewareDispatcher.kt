package com.emv.udf.middleware

import com.emv.datalayer.cache.CacheFactory
import com.emv.udf.actions.Action
import com.emv.udf.viewstate.AppViewState
import kotlinx.serialization.ImplicitReflectionSerializer

@ImplicitReflectionSerializer
class MiddlewareDispatcher constructor(cacheFactory: CacheFactory, private val emit: Emit) {

    private val middleware: Middleware = Middleware(emit, cacheFactory)

    fun dispatch(appViewState: AppViewState, action: Action) {
        when (action) {
            is Action.OnInit, Action.OnHome, Action.OnMesh, Action.OnMap -> {
                if (appViewState.responseViewState.isLoading == false
                        && appViewState.responseViewState.model == null) {
                    middleware.loadData()
                } else {
                    emit(action)
                }
            }
            else -> {
                emit(action)
            }
        }
    }

    fun clear() {
        middleware.clear()
    }
}