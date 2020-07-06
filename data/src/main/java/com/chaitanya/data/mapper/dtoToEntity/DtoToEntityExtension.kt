package com.chaitanya.data.mapper.dtoToEntity

import com.chaitanya.data.remote.dto.ErrorDto
import com.chaitanya.domain.entity.HomeEntity

/**
 * Created by Chaitanya Aggarwal on 05/28/2020.
 * Extension class to map  dto to  Entity
 */
fun ErrorDto.ErrorResponse.map() = HomeEntity.ErrorEntity(
    errors.map {
        it.map()
    }
)

fun ErrorDto.Error.map() = HomeEntity.Error(
    errorCode = errorCode,
    errorMessage = errorMessage
)

