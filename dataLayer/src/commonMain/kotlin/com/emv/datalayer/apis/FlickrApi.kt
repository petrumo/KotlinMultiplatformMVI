package com.emv.datalayer

import com.emv.datalayer.models.Feed
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.client.request.url

internal class FlickrApi() {

    private val client = HttpClient {
        install(JsonFeature)
    }

    suspend fun fetchFeed(): Feed {
        return client.get {
            url("$baseUrl?tags=kitten&format=json&nojsoncallback=1")
        }
    }

    companion object {
        private const val baseUrl = "https://www.flickr.com/services/feeds/photos_public.gne"
    }
}