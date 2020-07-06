package com.chaitanya.casino.di.module.application

import com.chaitanya.data.remote.api.IHomeApi
import com.chaitanya.data.repository.HomeRepositoryImpl
import com.chaitanya.domain.repository.home.HomeRepository
import dagger.Module
import dagger.Provides

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */

@Module(includes = [ApiModule::class])
class RepositoryModule {

    @Provides
    fun provideHomeRepository(api: IHomeApi): HomeRepository {
        return HomeRepositoryImpl(api)
    }
}