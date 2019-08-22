package com.emv.udf.store

import com.emv.datalayer.cache.CacheFactory
import com.emv.udf.actions.Action
import com.emv.udf.middleware.MiddlewareDispatcher
import com.emv.udf.reducer.Reducer
import com.emv.udf.viewstate.AppViewState
import com.emv.udf.viewstate.NavigationViewState
import com.emv.udf.viewstate.ResponseViewState
import kotlinx.serialization.ImplicitReflectionSerializer

typealias StateChangeListener = (AppViewState) -> Unit
@ImplicitReflectionSerializer
open class Store constructor(cacheFactory: CacheFactory, private val stateChangeListener: StateChangeListener) {
    private var responseViewState = ResponseViewState()
    private var navigationState = NavigationViewState()
    private var appViewState = AppViewState(navigationState, responseViewState)

    private val middlewareDispatcher: MiddlewareDispatcher = MiddlewareDispatcher(cacheFactory) { action: Action ->
        appViewState = Reducer.reduce(appViewState, action)
        stateChangeListener(appViewState)
    }

    fun dispatch(action: Action) {
        middlewareDispatcher.dispatch(appViewState, action)
    }

    fun clear() {
        middlewareDispatcher.clear()
    }
}