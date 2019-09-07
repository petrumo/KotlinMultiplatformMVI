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

@ImplicitReflectionSerializer
class ResponseViewModel : ViewModel(), KoinComponent {

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
        super.onCleared()
    }
}