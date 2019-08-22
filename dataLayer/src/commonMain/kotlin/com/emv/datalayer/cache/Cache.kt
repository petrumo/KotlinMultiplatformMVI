package com.emv.datalayer.cache

abstract class Cache<Value : Any> {
    abstract suspend fun get(): Value?
    abstract suspend fun set(value: Value)
    abstract suspend fun delete()
    abstract suspend fun clearAll()
}