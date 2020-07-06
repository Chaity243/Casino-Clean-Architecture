package com.chaitanya.domain.entity


/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */
sealed class HomeEntity {

    data class SearchResponse(
        val requestId: String,
        val data: Data
    ) : HomeEntity()

    data class Address(
        val id: String,
        val pinCode: Int,
        val city: String,
        val cityBoundaryBreached: Boolean,
        val pinCodeBoundaryBreached: Boolean,
        val addressType: String?,
        val addressString: String,
        val latitude: Double,
        val longitude: Double,
        val errorMargin: Int
    ) : HomeEntity()

    data class Data(
        val autoCompleteRequestString: String,
        val focusWord: String,
        val addressList: ArrayList<Address>
    ) : HomeEntity()


    data class ErrorEntity(val errors: List<Error>) : HomeEntity()

    data class Error(val errorCode: String, val errorMessage: String) : HomeEntity()



}