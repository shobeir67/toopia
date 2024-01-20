package com.shobeir.toopia.data.remote

import com.shobeir.toopia.data.model.City
import com.shobeir.toopia.data.model.Data
import com.shobeir.toopia.data.model.ModelPish
import com.shobeir.toopia.data.model.ModelTeam
import com.shobeir.toopia.data.model.News
import com.shobeir.toopia.data.model.ResponseResult
import com.shobeir.toopia.data.model.Slider
import com.shobeir.toopia.data.model.User
import com.shobeir.toopia.data.model.nobatdehi.ModelZaman
import com.shobeir.toopia.data.model.Store
import com.shobeir.toopia.data.model.nobatdehi.Status
import com.shobeir.toopia.ui.screen.news.details.ImagePost
import retrofit2.Response
import retrofit2.http.*

interface HomeApiInterface {
    @GET("get-slider.php")
    suspend fun getSlider() : Response<ResponseResult<List<Slider>>>

    @GET("get-all-news.php")
    suspend fun getAllNews() : Response<ResponseResult<List<News>>>

    @GET("get-city.php")
    suspend fun getCity() : Response<ResponseResult<List<City>>>

    @GET("get-all-shoping.php")
    suspend fun getAllShoping() : Response<ResponseResult<List<Store>>>

    @GET("get-store-user.php")
    suspend fun getStoreUser(@Query("phone") phone:String) : Response<ResponseResult<Store>>

    @GET("get-all-image-news.php")
    suspend fun getAllImageNews(@Query("code") code:String) : Response<ResponseResult<List<ImagePost>>>

    @GET("get-result.php")
    suspend fun getResult() : Response<ResponseResult<ModelPish>>

    @GET("get-winner.php")
    suspend fun getWinner() : Response<ResponseResult<User>>

    @GET("get-team.php")
    suspend fun getTeam() : Response<ResponseResult<ModelTeam>>

    @FormUrlEncoded
    @POST("register.php")
    suspend fun register(@Field("phone") phone:String, @Field("code") code:String):Response<ResponseResult<Data>>

    @FormUrlEncoded
    @POST("set-score.php")
    suspend fun setScore(@Field("phone") phone:String, @Field("score") score:String):Response<ResponseResult<Data>>


    @FormUrlEncoded
    @POST("add-user.php")
    suspend fun addUser(@Field("phone") phone:String):Response<ResponseResult<Data>>


    @GET("get-price-user.php")
    suspend fun getUser(@Query("phone") phone:String):Response<ResponseResult<User>>

    @GET("set-price-user-nobat.php")
    suspend fun setPriceUserNobat(@Query("phone") phone:String):Response<ResponseResult<Data>>

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


    @FormUrlEncoded
    @POST("add-nobat.php")
    suspend fun addNobat(
        @Field("code") code:String,
        @Field("phone") mobile:String,
        @Field("zaman") zaman:String,
    ): Response<Status>


    @GET("get-sanse-nobat.php")
    suspend fun getSanse(): Response<ModelZaman>

}