package com.shobeir.toopia.data.remote


import com.shobeir.toopia.data.model.Slider
import com.shobeir.toopia.data.model.Status
import retrofit2.http.*


interface ApiService {

    @FormUrlEncoded
    @POST("sendsms.php")
    suspend fun sendSms(@Field("mobile") mobile:String,@Field("code") code:String): Status

    @FormUrlEncoded
    @POST("addkasebi.php")
    suspend fun addPost(
        @Field("title") title:String,
        @Field("name") name:String,
        @Field("codemelli") codemelli:String,
        @Field("phone") phone:String,
        @Field("address") address:String,
        @Field("desc") desc:String,
    ):Status

    @FormUrlEncoded
    @POST("updatepost.php")
    suspend fun updatePost(
        @Field("title") title:String,
        @Field("phone") phone:String,
        @Field("address") address:String,
        @Field("desc") desc:String,
    ):Status

    @FormUrlEncoded
    @POST("getimagepost.php")
    suspend fun getGalleryPosts(@Field("id_post")id_post:String):List<Slider>
}