package com.chaitanya.domain.usecases.home

import com.chaitanya.domain.common.ResultState
import com.chaitanya.domain.entity.HomeEntity
import com.chaitanya.domain.repository.home.HomeRepository
import io.reactivex.Single

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */
class HomeUseCaseImpl(private val homeRepository: HomeRepository) : HomeUseCase {
    override fun fetchAddresses(
        queryString: String,
        city: String
    ): Single<ResultState<HomeEntity.SearchResponse>> {
        return homeRepository.fetchAddresses(queryString, city)
    }
}
