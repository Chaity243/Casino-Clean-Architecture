package com.chaitanya.casino.di.module.application

import android.content.Context
import com.chaitanya.casino.App
import com.chaitanya.casino.di.qualifier.ApplicationContext
import dagger.Binds
import dagger.Module

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */
@Module
abstract class AppModule {

    @ApplicationContext
    @Binds
    abstract fun provideApplicationContext(myApplication: App): Context

}