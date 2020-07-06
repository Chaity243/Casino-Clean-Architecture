package com.chaitanya.data.local.entity

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */

data class AddressEntity(
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
)