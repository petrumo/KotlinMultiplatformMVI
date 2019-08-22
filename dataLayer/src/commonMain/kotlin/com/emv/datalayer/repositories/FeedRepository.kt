package com.emv.datalayer.repositories

import com.emv.datalayer.cache.CacheFactory
import com.emv.datalayer.models.Feed
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.serializer

@ImplicitReflectionSerializer
class FeedRepository (cacheFactory: CacheFactory) {
    private val nmApi = com.emv.datalayer.FlickrApi()
    private val cache = cacheFactory.buildCache(Feed::class.serializer())

    suspend fun fetchFeed() : Feed {
        val feed = cache.get()
        if (feed != null) {
            return feed
        } else {
            val feed = nmApi.fetchFeed()
            cache.set(feed)
            return feed
        }
    }

    suspend fun clearAll() {
        cache.clearAll()
    }
}