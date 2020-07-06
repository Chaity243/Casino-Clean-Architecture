package com.chaitanya.data.local.entity

import com.chaitanya.domain.entity.HomeEntity

/**
 * Created by Chaitanya Aggarwal on 28/5/2020.
 */
class DataEntity(
    val autoCompleteRequestString: String,
    val focusWord: String,
    val addressList: ArrayList<HomeEntity.Address>
)