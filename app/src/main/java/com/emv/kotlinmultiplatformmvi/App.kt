package com.emv.kotlinmultiplatformmvi

import androidx.multidex.MultiDexApplication
import com.emv.datalayer.cache.CacheFactory
import com.emv.datalayer.cache.CacheFactoryImpl
import com.emv.datalayer.cache.DiskUtil
import kotlinx.serialization.ImplicitReflectionSerializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

@ImplicitReflectionSerializer
val myModule = module {
    single<CacheFactory> {CacheFactoryImpl(DiskUtil.file)}
}

@ImplicitReflectionSerializer
class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        DiskUtil.initDiskCacheFolder(true, this)
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(myModule)
        }
    }
}
