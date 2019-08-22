package com.emv.udf.middleware

import com.emv.datalayer.cache.CacheFactory
import com.emv.datalayer.models.Feed
import com.emv.datalayer.repositories.FeedRepository
import com.emv.udf.results.DataResult
import kotlinx.serialization.ImplicitReflectionSerializer

@ImplicitReflectionSerializer
class Middleware constructor(emit: Emit, cacheFactory: CacheFactory) : BaseMiddleware<Feed>(emit) {
    private val ERROR = "error"
    private val repository: FeedRepository = FeedRepository(cacheFactory)

    override suspend fun getData(): DataResult<Feed> {
        try {
            val response = repository.fetchFeed()
            return DataResult.Success(response)
        } catch (t: Throwable) {
            return DataResult.Failure(ERROR)
        }

        return DataResult.Failure("Failure to retrieve movies.")
    }
}