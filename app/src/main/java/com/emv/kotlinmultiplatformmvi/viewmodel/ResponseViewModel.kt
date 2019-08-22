package com.emv.kotlinmultiplatformmvi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emv.datalayer.cache.CacheFactory
import com.emv.udf.actions.Action
import com.emv.udf.store.Store
import com.emv.udf.viewstate.AppViewState
import kotlinx.serialization.ImplicitReflectionSerializer
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*
import kotlin.collections.HashMap
import kotlin.concurrent.fixedRateTimer

@ImplicitReflectionSerializer
class ResponseViewModel : ViewModel(), KoinComponent {

    private val refreshTimers : HashMap<String, Timer> = HashMap()
    companion object {
      private val TIMER_UPDATE_INTERVAL = 60000L
    }

    private val cacheFactory: CacheFactory by inject()
    private val store: Store = Store(cacheFactory) { appViewState ->
        appStateLD.value = appViewState
    }
    private val appStateLD = MutableLiveData<AppViewState>()

    val appState: LiveData<AppViewState>
        get() = appStateLD


    fun dispatch(action: Action) {
        store.dispatch(action)
    }

    override fun onCleared() {
        store.clear()
        unregisterAll()
        super.onCleared()
    }

    fun registerRefresh(name: String, action: TimerTask.() -> Unit) {
        unregisterRefresh(name)
        val timer = fixedRateTimer(name, false, TIMER_UPDATE_INTERVAL, TIMER_UPDATE_INTERVAL, action)
        refreshTimers.put(name, timer)
    }

    fun unregisterRefresh(name: String) {
        refreshTimers[name]?.cancel()
        refreshTimers.remove(name)
    }

    private fun unregisterAll() {
        for (entry in refreshTimers) {
            entry.value.cancel()
        }
    }
}