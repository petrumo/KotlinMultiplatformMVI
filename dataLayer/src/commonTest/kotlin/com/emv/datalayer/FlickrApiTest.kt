package com.emv.datalayer

import kotlin.test.Test
import kotlin.test.assertNotNull

class FlickrApiTest {

    @Test
    fun testKittens() = runTest {
        var feed = FlickrApi().fetchFeed()
        assertNotNull(feed)
    }
}