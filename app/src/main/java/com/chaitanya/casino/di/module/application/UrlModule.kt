package com.chaitanya.casino.di.module.application

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */

@Module
class UrlModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return "" // Replace with actual base url
    }
}