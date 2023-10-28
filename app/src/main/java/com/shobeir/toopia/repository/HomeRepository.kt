package com.shobeir.toopia.repository

import com.shobeir.toopia.data.model.Data
import com.shobeir.toopia.data.remote.BaseApiResponse
import com.shobeir.toopia.data.remote.HomeApiInterface
import com.shobeir.toopia.data.remote.NetworkResult
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: HomeApiInterface) : BaseApiResponse() {

    suspend fun sendSms(phone: String, code: String): NetworkResult<Data> =
        safeApiCall {
            api.register(phone = phone, code = code)
        }


    suspend fun setPish(
        phone: String,
        goleOne: String,
        goleTow: String,
        yellowOne: String,
        yellowTow: String,
        redOne: String,
        redTow: String,
        malekiyatOne: String,
        malekiyatTow: String,
        cornerOne: String,
        cornerTow: String,
        khataOne: String,
        khataTow: String,
        afsaideOne: String,
        afsaideTow: String,
        shooteOne: String,
        shooteTow: String
    ): NetworkResult<Data> =
        safeApiCall {
            api.setPishUser(
                phone,
                goleOne,
                goleTow,
                yellowOne,
                yellowTow,
                redOne,
                redTow,
                malekiyatOne,
                malekiyatTow,
                cornerOne,
                cornerTow,
                khataOne,
                khataTow,
                afsaideOne,
                afsaideTow,
                shooteOne,
                shooteTow
            )
        }


}