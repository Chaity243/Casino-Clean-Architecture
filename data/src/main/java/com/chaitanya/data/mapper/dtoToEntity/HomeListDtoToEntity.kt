package com.chaitanya.data.mapper.dtoToEntity

import com.chaitanya.data.remote.dto.HomeDto
import com.chaitanya.domain.entity.HomeEntity

/**
 * Created by Chaitanya Aggarwal on 05/28/2020.
 * Extension class to map  dto to  Entity
 */

fun HomeDto.SearchResponse.map() = HomeEntity.SearchResponse(
    requestId = requestId,
    data = data.map()
)

fun HomeDto.Data.map() = HomeEntity.Data(
    autoCompleteRequestString = autoCompleteRequestString,
    focusWord = focusWord,
    addressList = addressList.map { it.map() } as ArrayList<HomeEntity.Address>
)

fun HomeDto.Address.map() = HomeEntity.Address(
    id = id,
    pinCode = pinCode,
    city = city,
    cityBoundaryBreached = cityBoundaryBreached,
    pinCodeBoundaryBreached = pinCodeBoundaryBreached,
    addressType = addressType,
    addressString = addressString,
    latitude = latitude,
    longitude = longitude,
    errorMargin = errorMargin
)