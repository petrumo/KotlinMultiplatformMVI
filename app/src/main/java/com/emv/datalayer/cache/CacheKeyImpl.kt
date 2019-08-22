package com.emv.datalayer.cache

class CacheKeyImpl<T : Any>(private val cache: com.appmattus.layercache.Cache<String, T>): CacheKey<String, T>() {
    override suspend fun clearAll() {
        cache.evictAll()
    }

    override suspend fun delete(key: String) {
        cache.evict(key)
    }

    override suspend fun get(key: String): T? {
        return cache.get(key).await()
    }

    override suspend fun set(key: String, value: T) {
        cache.set(key, value)
    }

}