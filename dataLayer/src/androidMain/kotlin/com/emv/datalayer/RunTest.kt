package com.emv.datalayer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

actual fun runTest(block: suspend (scope : CoroutineScope) -> Unit) = runBlocking { block(this) }