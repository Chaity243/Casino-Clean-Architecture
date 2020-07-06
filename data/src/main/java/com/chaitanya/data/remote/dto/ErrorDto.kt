package com.chaitanya.data.remote.dto

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */
sealed class ErrorDto {

    data class ErrorResponse(val error: Boolean, val errors: List<Error>) : ErrorDto()

    data class Error(val errorCode: String, val errorMessage: String) : ErrorDto()


}