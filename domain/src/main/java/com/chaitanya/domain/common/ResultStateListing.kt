package com.chaitanya.domain.common

import com.chaitanya.domain.entity.HomeEntity

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 * A wrapper for database and network states.
 */
sealed class ResultStateListing<T> {

    /**
     * A state of [data] which shows that we know there is still an update to come.
     */
    data class Loading<T>(val data: T) : ResultStateListing<T>()

    /**
     * A state that shows the [data] is the last known update.
     */
    data class Success<T>(val data: T) : ResultStateListing<T>()

    /**
     * A state to show a [throwable] is thrown.
     */
    data class Error<T>(val throwable: Throwable, val error: HomeEntity.ErrorEntity?) :
        ResultStateListing<T>()


}