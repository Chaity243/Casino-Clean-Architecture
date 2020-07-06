package com.chaitanya.domain.usecases.home

import com.chaitanya.domain.common.ResultState
import com.chaitanya.domain.entity.HomeEntity
import com.chaitanya.domain.usecases.BaseUseCase
import io.reactivex.Single

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */
interface HomeUseCase : BaseUseCase {

    /* Single Observable is used here as list of Addresses will be fetched at once.*/
    fun fetchAddresses(
        queryString: String,
        city: String
    ): Single<ResultState<HomeEntity.SearchResponse>>

}