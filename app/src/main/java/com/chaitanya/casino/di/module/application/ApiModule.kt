package com.chaitanya.casino.di.module.application

import com.chaitanya.data.remote.api.IHomeApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */

@Module(includes = [RetrofitModule::class])
class ApiModule {

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): IHomeApi {
        return retrofit.create(IHomeApi::class.java)
    }
}