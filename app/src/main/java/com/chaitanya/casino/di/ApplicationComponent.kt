package com.chaitanya.casino.di

import com.chaitanya.casino.App
import com.chaitanya.casino.di.module.activity.ActivityModule
import com.chaitanya.casino.di.module.application.*
import com.chaitanya.casino.di.module.fragment.FragmentModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/*
 * Created by Chaitanya Aggarwal on 28/5/2020.
 * We mark this interface with the @Component annotation.
 * And we define all the modules that can be injected.
 * Note that we provide AndroidSupportInjectionModule.class
 * here. This class was not created by us.
 * It is an internal class in Dagger 2.10.
 * Provides our activities and fragments with given module.
 * */

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ApiModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ActivityModule::class,
        FragmentModule::class,
        UrlModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}