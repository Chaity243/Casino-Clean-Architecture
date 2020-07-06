package com.chaitanya.casino.di.module.application

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chaitanya.casino.di.qualifier.ViewModelKey
import com.chaitanya.casino.presentation.ui.casino.CasinoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    /*
    * This method basically says
    * inject this object into a Map using the @IntoMap annotation,
    * with the  AuthViewModel.class as key,
    * and a Provider that will build a AuthViewModel
    * object.
    *
    * */

    @Binds
    @IntoMap
    @ViewModelKey(CasinoViewModel::class)
    internal abstract fun bindSearchViewModel(viewModel: CasinoViewModel): ViewModel

}