package com.shobeir.toopia.repository

import com.shobeir.toopia.data.remote.BaseApiResponse
import com.shobeir.toopia.data.remote.HomeApiInterface
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api : HomeApiInterface) : BaseApiResponse(){


}