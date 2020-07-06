package com.chaitanya.data.repository

import android.util.MalformedJsonException
import com.chaitanya.data.mapper.dtoToEntity.map
import com.chaitanya.data.remote.config.NetworkConfig
import com.chaitanya.data.remote.dto.ErrorDto
import com.chaitanya.domain.common.ResultState
import com.chaitanya.domain.entity.HomeEntity
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */


abstract class BaseRepositoryImpl {
    private val logFormatter: String = "%s | %s"
    internal fun handleErrorReturn(throwable: Throwable): ResultState.Error<ResultState<List<HomeEntity.Error>>> {
        Timber.tag("BaseRepositoryImpl")
        return when (throwable) {

            is HttpException -> {
                when (throwable.code()) {
                    NetworkConfig.RESPONSE_CODE_NOT_FOUND -> {
                        Timber.e(
                            logFormatter,
                            throwable.response().toString(),
                            NetworkConfig.ERROR_MESSAGE_NOT_FOUND
                        )
                        ResultState.Error(
                            throwable,
                            HomeEntity.ErrorEntity(
                                listOf(
                                    HomeEntity.Error(
                                        NetworkConfig.RESPONSE_CODE_NOT_FOUND.toString(),
                                        NetworkConfig.ERROR_MESSAGE_NOT_FOUND
                                    )
                                )
                            )
                        )
                    }
                    NetworkConfig.RESPONSE_CODE_DEACTIVATED -> {
                        Timber.e(
                            logFormatter,
                            throwable.response().toString(),
                            NetworkConfig.ERROR_MESSAGE_DEACTIVATED
                        )
                        ResultState.Error(
                            throwable,
                            HomeEntity.ErrorEntity(
                                listOf(
                                    HomeEntity.Error(
                                        NetworkConfig.RESPONSE_CODE_DEACTIVATED.toString(),
                                        NetworkConfig.ERROR_MESSAGE_DEACTIVATED
                                    )
                                )
                            )
                        )
                    }
                    NetworkConfig.RESPONSE_CODE_REQUEST_TIMEOUT -> {
                        Timber.e(
                            logFormatter,
                            throwable.response().toString(),
                            NetworkConfig.ERROR_MESSAGE_TIMEOUT_ERROR
                        )
                        ResultState.Error(
                            throwable,
                            HomeEntity.ErrorEntity(
                                listOf(
                                    HomeEntity.Error(
                                        NetworkConfig.RESPONSE_CODE_REQUEST_TIMEOUT.toString(),
                                        NetworkConfig.ERROR_MESSAGE_TIMEOUT_ERROR
                                    )
                                )
                            )
                        )
                    }
                    NetworkConfig.RESPONSE_CODE_NOT_IMPLEMENTED_ERROR -> {
                        Timber.e(
                            logFormatter,
                            throwable.response().toString(),
                            NetworkConfig.ERROR_MESSAGE_NOT_IMPLEMENTED_ERROR
                        )
                        ResultState.Error(
                            throwable,
                            HomeEntity.ErrorEntity(
                                listOf(
                                    HomeEntity.Error(
                                        NetworkConfig.RESPONSE_CODE_NOT_IMPLEMENTED_ERROR.toString(),
                                        NetworkConfig.ERROR_MESSAGE_NOT_IMPLEMENTED_ERROR
                                    )
                                )
                            )
                        )
                    }
                    NetworkConfig.RESPONSE_CODE_INTERNAL_SERVER_ERROR -> {
                        Timber.e(
                            logFormatter,
                            throwable.response().toString(),
                            NetworkConfig.ERROR_MESSAGE_INTERNAL_SERVER_ERROR
                        )
                        ResultState.Error(
                            throwable,
                            HomeEntity.ErrorEntity(
                                listOf(
                                    HomeEntity.Error(
                                        NetworkConfig.RESPONSE_CODE_INTERNAL_SERVER_ERROR.toString(),
                                        NetworkConfig.ERROR_MESSAGE_INTERNAL_SERVER_ERROR
                                    )
                                )
                            )
                        )
                    }
                    else -> {
                        // TODO Remove try catch and handle error according to error code, Having dependency on server
                        try {
                            Timber.e(
                                logFormatter,
                                throwable.response()?.toString() ?: throwable.message().toString(),
                                NetworkConfig.ERROR_MESSAGE_GENERAL_UNKNOWN
                            )
                            val error: ErrorDto.ErrorResponse? =
                                throwable.response()?.let {
                                    it.errorBody()?.let { response -> getError(response) }
                                }
                            if (error != null) {
                                ResultState.Error<ResultState<List<HomeEntity.Error>>>(
                                    throwable,
                                    error.map()
                                )
                            } else {
                                ResultState.Error(
                                    throwable,
                                    HomeEntity.ErrorEntity(
                                        listOf(
                                            HomeEntity.Error(
                                                NetworkConfig.RESPONSE_CODE_UNEXPECTED.toString(),
                                                NetworkConfig.ERROR_MESSAGE_UNEXPECTED
                                            )
                                        )
                                    )
                                )
                            }

                        } catch (e: Exception) {
                            Timber.e(e)
                            ResultState.Error<ResultState<List<HomeEntity.Error>>>(
                                throwable,
                                HomeEntity.ErrorEntity(
                                    listOf(
                                        HomeEntity.Error(
                                            NetworkConfig.RESPONSE_CODE_GENERAL_UNKNOWN_ERROR.toString(),
                                            NetworkConfig.ERROR_MESSAGE_GENERAL_UNKNOWN
                                        )
                                    )
                                )
                            )
                        }
                    }
                }
            }
            is ConnectException, is UnknownHostException -> {
                Timber.e(
                    logFormatter,
                    throwable.message.toString(),
                    NetworkConfig.ERROR_MESSAGE_GENERAL_NETWORK
                )
                ResultState.Error(
                    throwable,
                    HomeEntity.ErrorEntity(
                        listOf(
                            HomeEntity.Error(
                                NetworkConfig.RESPONSE_CODE_GENERAL_NETWORK_ERROR.toString(),
                                NetworkConfig.ERROR_MESSAGE_GENERAL_NETWORK
                            )
                        )
                    )
                )
            }

            is IOException -> {
                Timber.e(
                    logFormatter,
                    throwable.message.toString(),
                    NetworkConfig.ERROR_MESSAGE_GENERAL_UNKNOWN
                )
                ResultState.Error(
                    throwable,
                    HomeEntity.ErrorEntity(
                        listOf(
                            HomeEntity.Error(
                                NetworkConfig.RESPONSE_CODE_GENERAL_UNKNOWN_ERROR.toString(),
                                NetworkConfig.ERROR_MESSAGE_GENERAL_UNKNOWN
                            )
                        )
                    )
                )
            }

            is SocketTimeoutException -> {
                Timber.e(
                    logFormatter,
                    throwable.message.toString(),
                    NetworkConfig.ERROR_MESSAGE_SYSTEM_UNAVAILABLE
                )
                ResultState.Error(
                    throwable,
                    HomeEntity.ErrorEntity(
                        listOf(
                            HomeEntity.Error(
                                NetworkConfig.RESPONSE_CODE_GENERAL_SYSTEM_UNAVAILABLE_ERROR.toString(),
                                NetworkConfig.ERROR_MESSAGE_SYSTEM_UNAVAILABLE
                            )
                        )
                    )
                )
            }

            is MalformedJsonException -> {
                Timber.e(
                    logFormatter,
                    throwable.message.toString(),
                    NetworkConfig.ERROR_MESSAGE_TRY_AGAIN_LATER
                )
                ResultState.Error(
                    throwable,
                    HomeEntity.ErrorEntity(
                        listOf(
                            HomeEntity.Error(
                                NetworkConfig.RESPONSE_CODE_GENERAL_TRY_AGAIN_LATER_ERROR.toString(),
                                NetworkConfig.ERROR_MESSAGE_TRY_AGAIN_LATER
                            )
                        )
                    )
                )
            }

            is NoSuchElementException -> {
                Timber.e(
                    logFormatter,
                    throwable.message.toString(),
                    NetworkConfig.ERROR_CODE_NO_FOUND
                )
                ResultState.Error(
                    throwable,
                    HomeEntity.ErrorEntity(
                        listOf(
                            HomeEntity.Error(
                                NetworkConfig.RESPONSE_CODE_NO_CONTENT.toString(),
                                NetworkConfig.ERROR_CODE_NO_FOUND
                            )
                        )
                    )
                )
            }
            else -> {
                Timber.e(
                    logFormatter,
                    throwable.message.toString(),
                    NetworkConfig.ERROR_MESSAGE_UNEXPECTED
                )
                ResultState.Error(
                    throwable,
                    HomeEntity.ErrorEntity(
                        listOf(
                            HomeEntity.Error(
                                NetworkConfig.RESPONSE_CODE_UNEXPECTED.toString(),
                                NetworkConfig.ERROR_MESSAGE_UNEXPECTED
                            )
                        )
                    )
                )
            }
        }
    }

    internal fun <T> createNetworkError() = ResultState.Error<T>(
        Throwable(NetworkConfig.ERROR_DATA_NOT_FOUND), HomeEntity.ErrorEntity(
            listOf(
                HomeEntity.Error(
                    NetworkConfig.RESPONSE_CODE_NO_DATA.toString(),
                    NetworkConfig.ERROR_DATA_NOT_FOUND
                )
            )
        )
    )

    private fun getError(responseBody: ResponseBody): ErrorDto.ErrorResponse {
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        return gson.fromJson(responseBody.string(), ErrorDto.ErrorResponse::class.java)
    }
}