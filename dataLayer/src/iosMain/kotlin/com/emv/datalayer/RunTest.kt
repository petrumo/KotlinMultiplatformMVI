package com.emv.datalayer

import kotlinx.coroutines.*

actual fun runTest(block: suspend (scope : CoroutineScope) -> Unit){
    runBlocking {
        block(this)
    }
}