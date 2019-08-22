package com.emv.datalayer

import kotlinx.coroutines.CoroutineScope

expect fun runTest(block: suspend (scope : CoroutineScope) -> Unit)