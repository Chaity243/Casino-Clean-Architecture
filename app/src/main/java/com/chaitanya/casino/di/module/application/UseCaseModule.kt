package com.chaitanya.casino.di.module.application

import com.chaitanya.domain.repository.home.HomeRepository
import com.chaitanya.domain.usecases.home.HomeUseCase
import com.chaitanya.domain.usecases.home.HomeUseCaseImpl
import dagger.Module
import dagger.Provides
/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */

@Module
class UseCaseModule {
    @Provides
    fun provideHomeUseCaseImpl(repository: HomeRepository): HomeUseCase =
        HomeUseCaseImpl(repository)

}