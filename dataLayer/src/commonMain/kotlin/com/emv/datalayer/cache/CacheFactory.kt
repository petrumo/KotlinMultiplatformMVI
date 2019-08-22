package com.emv.datalayer.cache

import kotlinx.serialization.KSerializer

abstract class CacheFactory {
    protected val CACHE_VERSION = 1

    //duration : Int
    abstract fun <T : Any> buildCache(
        serializer: KSerializer<T>,
        protected: Boolean = false,
        memOnly: Boolean = false,
        deleteExpired: Boolean = true
    ): Cache<T>

    abstract fun <T : Any> buildCacheKey(
        serializer: KSerializer<T>,
        protected: Boolean = false,
        memOnly: Boolean = false,
        deleteExpired: Boolean = true
    ): CacheKey<String, T>

}