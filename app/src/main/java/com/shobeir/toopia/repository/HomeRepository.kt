package com.shobeir.toopia.repository

import com.shobeir.toopia.data.model.City
import com.shobeir.toopia.data.model.Data
import com.shobeir.toopia.data.model.ModelPish
import com.shobeir.toopia.data.model.ModelTeam
import com.shobeir.toopia.data.model.News
import com.shobeir.toopia.data.model.Shoping
import com.shobeir.toopia.data.model.Slider
import com.shobeir.toopia.data.model.User
import com.shobeir.toopia.data.remote.BaseApiResponse
import com.shobeir.toopia.data.remote.HomeApiInterface
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.ui.screen.news.details.ImagePost
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: HomeApiInterface) : BaseApiResponse() {

    suspend fun getSlider(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getSlider()
        }

    suspend fun getAllNews(): NetworkResult<List<News>> =
        safeApiCall {
            api.getAllNews()
        }

    suspend fun getCity(): NetworkResult<List<City>> =
        safeApiCall {
            api.getCity()
        }

    suspend fun getAllNews(code: String): NetworkResult<List<ImagePost>> =
        safeApiCall {
            api.getAllImageNews(code)
        }

    suspend fun getWinner(): NetworkResult<User> =
        safeApiCall {
            api.getWinner()
        }

    suspend fun sendSms(phone: String, code: String): NetworkResult<Data> =
        safeApiCall {
            api.register(phone = phone, code = code)
        }

    suspend fun setScore(phone: String, score: String): NetworkResult<Data> =
        safeApiCall {
            api.setScore(phone = phone, score = score)
        }

    suspend fun addUser(phone: String,): NetworkResult<Data> =
        safeApiCall {
            api.addUser(phone = phone)
        }

    suspend fun getPishUser(phone: String,): NetworkResult<ModelPish> =
        safeApiCall {
            api.getPishUser(phone = phone)
        }

    suspend fun getPriceUser(phone: String,): NetworkResult<User> =
        safeApiCall {
            api.getUser(phone = phone)
        }

    suspend fun setPriceUserNobat(phone: String,): NetworkResult<Data> =
        safeApiCall {
            api.setPriceUserNobat(phone)
        }


    suspend fun getResult(): NetworkResult<ModelPish> =
        safeApiCall {
            api.getResult()
        }
    suspend fun getTeam(): NetworkResult<ModelTeam> =
        safeApiCall {
            api.getTeam()
        }

    suspend fun getAllShoping(): NetworkResult<List<Shoping>> =
        safeApiCall {
            api.getAllShoping()
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