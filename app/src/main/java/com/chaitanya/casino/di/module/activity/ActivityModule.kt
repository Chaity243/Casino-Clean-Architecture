package com.chaitanya.casino.di.module.activity

import com.chaitanya.casino.presentation.ui.casino.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}