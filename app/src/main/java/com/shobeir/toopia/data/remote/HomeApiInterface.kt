package com.shobeir.toopia.data.remote

import com.shobeir.toopia.data.model.Data
import com.shobeir.toopia.data.model.ModelPish
import com.shobeir.toopia.data.model.ResponseResult
import com.shobeir.toopia.data.model.Slider
import retrofit2.Response
import retrofit2.http.*

interface HomeApiInterface {
    @GET("get-slider.php")
    suspend fun getSlider() : Response<ResponseResult<List<Slider>>>

    @FormUrlEncoded
    @POST("register.php")
    suspend fun register(@Field("phone") phone:String, @Field("code") code:String):Response<ResponseResult<Data>>

    @FormUrlEncoded
    @POST("add-user.php")
    suspend fun addUser(@Field("phone") phone:String):Response<ResponseResult<Data>>

    @FormUrlEncoded
    @POST("get-pish-user.php")
    suspend fun getPishUser(@Field("phone") phone:String):Response<ResponseResult<ModelPish>>

    @FormUrlEncoded
    @POST("set-pish-user.php")
    suspend fun setPishUser(
        @Field("phone") phone:String,
        @Field("goleOne") goleOne:String,
        @Field("goleTow") goleTow:String,
        @Field("yellowOne") yellowOne:String,
        @Field("yellowTow") yellowTow:String,
        @Field("redOne") redOne:String,
        @Field("redTow") redTow:String,
        @Field("malekiyatOne") malekiyatOne:String,
        @Field("malekiyatTow") malekiyatTow:String,
        @Field("cornerOne") cornerOne:String,
        @Field("cornerTow") cornerTow:String,
        @Field("khataOne") khataOne:String,
        @Field("khataTow") khataTow:String,
        @Field("afsaideOne") afsaideOne:String,
        @Field("afsaideTow") afsaideTow:String,
        @Field("shooteOne") shooteOne:String,
        @Field("shooteTow") shooteTow:String
    ):Response<ResponseResult<Data>>


}