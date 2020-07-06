package com.chaitanya.data.remote.api

import com.chaitanya.data.remote.dto.HomeDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */

interface IHomeApi {

    // Get API to fetch all available addresses against searched query string and city

    @GET("compassLocation/rest/address/autocomplete")
    fun fetchSearchResults(
        @Query("queryString") queryString: String,
        @Query("city") city: String
    ): Single<HomeDto.SearchResponse>
}