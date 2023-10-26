package com.shobeir.toopia.repository

import com.shobeir.toopia.data.model.Status
import com.shobeir.toopia.data.remote.BaseApiResponse
import com.shobeir.toopia.data.remote.HomeApiInterface
import com.shobeir.toopia.data.remote.NetworkResult
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api : HomeApiInterface) : BaseApiResponse(){

    suspend fun sendSms(phone:String,code:String): NetworkResult<Status> =
        safeApiCall {
            api.register(phone=phone,code=code)
        }


}