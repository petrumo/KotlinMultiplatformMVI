package com.emv.datalayer.cache

abstract class CacheKey<Key : String, Value : Any> {
    abstract suspend fun get(key: Key): Value?
    abstract suspend fun set(key: Key, value: Value)
    abstract suspend fun delete(key: Key)
    abstract suspend fun clearAll()
}