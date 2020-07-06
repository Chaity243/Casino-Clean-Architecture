package com.chaitanya.data.repository

import com.chaitanya.data.mapper.dtoToEntity.map
import com.chaitanya.data.remote.api.IHomeApi
import com.chaitanya.domain.common.ResultState
import com.chaitanya.domain.entity.HomeEntity
import com.chaitanya.domain.repository.home.HomeRepository
import com.chaitanya.data.extension.applyIoScheduler
import io.reactivex.Single

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */

class HomeRepositoryImpl(private val api: IHomeApi) : BaseRepositoryImpl(), HomeRepository {

    override fun fetchAddresses(
        queryString: String,
        city: String
    ): Single<ResultState<HomeEntity.SearchResponse>> {
        return api.fetchSearchResults(queryString, city).applyIoScheduler()
            .map {
                ResultState.Success(it.map()) as ResultState<HomeEntity.SearchResponse>
            }.onErrorReturn {
                handleErrorReturn(it) as ResultState<HomeEntity.SearchResponse>
            }
    }
}


