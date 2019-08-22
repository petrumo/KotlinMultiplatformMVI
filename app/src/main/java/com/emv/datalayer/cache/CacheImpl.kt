package com.emv.datalayer.cache

class CacheImpl<T : Any>(private val cache: com.appmattus.layercache.Cache<String, T>) : Cache<T>() {
    private val STATIC_KEY = "key"
    override suspend fun clearAll() {
        cache.evictAll()
    }

    override suspend fun delete() {
        cache.evict(STATIC_KEY)
    }

    override suspend fun get(): T? {
        try {
            return cache.get(STATIC_KEY).await()
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return null
    }

    override suspend fun set(value: T) {
        cache.set(STATIC_KEY, value)
    }
}