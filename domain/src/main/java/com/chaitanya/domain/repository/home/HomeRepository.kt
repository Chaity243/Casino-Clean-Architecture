package com.chaitanya.domain.repository.home

import com.chaitanya.domain.common.ResultState
import com.chaitanya.domain.entity.HomeEntity
import com.chaitanya.domain.repository.BaseRepository
import io.reactivex.Single

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */
interface HomeRepository : BaseRepository {

    fun fetchAddresses(
        queryString: String,
        city: String
    ): Single<ResultState<HomeEntity.SearchResponse>>

}