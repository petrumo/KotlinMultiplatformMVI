package com.emv.datalayer.cache

import com.appmattus.layercache.createDiskLruCache
import com.appmattus.layercache.createLruCache
import com.appmattus.layercache.jsonSerializer
import kotlinx.serialization.KSerializer
import java.io.File


class CacheFactoryImpl(private val cacheDirectory: File) : CacheFactory() {
    private val MAX_SIZE_MB: Long = 10 * 1024 * 1024
    private val MAX_SIZE = 10
    //TODO add expiry and version to clean all the cache

    override fun <T : Any> buildCache(
        serializer: KSerializer<T>,
        protected: Boolean,
        memOnly: Boolean,
        deleteExpired: Boolean
    ): Cache<T> {
        return CacheImpl(getCaching(memOnly, serializer))
    }

    override fun <T : Any> buildCacheKey(
        serializer: KSerializer<T>,
        protected: Boolean,
        memOnly: Boolean,
        deleteExpired: Boolean
    ): CacheKey<String, T> {
        return CacheKeyImpl(getCaching(memOnly, serializer))
    }

    private inline fun <T : Any> getCaching(
        memOnly: Boolean,
        serializer: KSerializer<T>
    ): com.appmattus.layercache.Cache<String, T> {
        val memoryCache: com.appmattus.layercache.Cache<String, T> =
            com.appmattus.layercache.Cache.createLruCache(MAX_SIZE)
        return if (memOnly) memoryCache else
            memoryCache.compose(
                com.appmattus.layercache.Cache.createDiskLruCache(cacheDirectory, MAX_SIZE_MB)
                    .jsonSerializer(serializer)
            )
    }
}