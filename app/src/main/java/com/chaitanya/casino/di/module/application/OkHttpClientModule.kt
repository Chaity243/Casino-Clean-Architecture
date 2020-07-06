package com.chaitanya.casino.di.module.application

import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */

@Module
class OkHttpClientModule {
    private val CONNECT_TIMEOUT: Long = 30
    private val CONNECT_TIME_UNIT = TimeUnit.SECONDS
    private val READ_TIMEOUT: Long = 30
    private val READ_TIME_UNIT = TimeUnit.SECONDS

    /*
     * The method returns the Okhttp object
     **/
    @Provides
    @Singleton
    fun okHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient()
            .newBuilder()
            .dispatcher(dispatcher)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, CONNECT_TIME_UNIT)
            .readTimeout(READ_TIMEOUT, READ_TIME_UNIT)


        return okHttpClientBuilder.build()
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


}