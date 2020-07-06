package com.chaitanya.casino

import android.content.Context
import com.chaitanya.casino.di.DaggerApplicationComponent
import com.facebook.ads.AudienceNetworkAds
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }

    init {
        /**
         * Application Instance
         */
        instance = this
    }

    companion object {
        private lateinit var instance: App
        fun applicationContext(): Context {
            return instance.applicationContext
        }

        fun getInstance(): App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        // Initializing Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Initialize the Audience Network SDK
        AudienceNetworkAds.initialize(this)

    }
}