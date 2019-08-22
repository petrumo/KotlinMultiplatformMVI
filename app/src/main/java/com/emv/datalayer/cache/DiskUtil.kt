package com.emv.datalayer.cache

import android.content.Context
import java.io.File

class DiskUtil {
    companion object {
        private val CACHE_FILE_PREFIX = "coroutinescache"
        private val id = "FLICKR"
        lateinit var file : File
        fun initDiskCacheFolder(usePrivateFiles: Boolean, context: Context){
            if (usePrivateFiles) {
                file = context.getDir(
                    CACHE_FILE_PREFIX + this.id,
                    Context.MODE_PRIVATE
                )
            } else {
                file = File(
                    context.cacheDir.path
                            + "/" + CACHE_FILE_PREFIX
                            + "/" + this.id
                )
            }
        }
    }
}