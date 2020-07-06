package com.chaitanya.data.remote.dto

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */
sealed class HomeDto {

    data class SearchResponse(
        val requestId: String,
        val data: Data
    ) : HomeDto()

    data class Data(
        val autoCompleteRequestString: String,
        val focusWord: String,
        val addressList: ArrayList<Address>
    ) : HomeDto()

    data class Address(
        val id: String,
        val pinCode: Int,
        val city: String,
        val cityBoundaryBreached: Boolean,
        val pinCodeBoundaryBreached: Boolean,
        val addressType: String,
        val addressString: String,
        val latitude: Double,
        val longitude: Double,
        val errorMargin: Int
    ) : HomeDto()


}

