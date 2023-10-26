package com.shobeir.toopia.data.remote

import com.shobeir.toopia.data.model.ResponseResult
import com.shobeir.toopia.data.model.Slider
import com.shobeir.toopia.data.model.Status
import retrofit2.Response
import retrofit2.http.*

interface HomeApiInterface {
    @GET("get-slider.php")
    suspend fun getSlider() : Response<ResponseResult<List<Slider>>>

    @POST("register.php")
    suspend fun register(@Field("phone") phone:String, @Field("code") code:String):Response<ResponseResult<Status>>
}