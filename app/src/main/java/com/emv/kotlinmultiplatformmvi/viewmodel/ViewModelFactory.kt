package com.emv.kotlinmultiplatformmvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.serialization.ImplicitReflectionSerializer

@ImplicitReflectionSerializer
class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return ResponseViewModel() as T
    }

}