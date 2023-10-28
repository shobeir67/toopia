package com.shobeir.toopia.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobeir.toopia.data.model.Data
import com.shobeir.toopia.data.model.ModelPish
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.repository.HomeRepository
import com.shobeir.toopia.ui.screen.login.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PishViewModel @Inject constructor(private val repository: HomeRepository) :
    ViewModel() {

    val loginResponse = MutableStateFlow<NetworkResult<Data>>(NetworkResult.Loading())
    val pishResponse = MutableStateFlow<NetworkResult<ModelPish>>(NetworkResult.Loading())

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
    ) {
        viewModelScope.launch {
            loginResponse.emit(
                repository.setPish(
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
            )
        }
    }

     fun getPish(phone: String){
         viewModelScope.launch {
            pishResponse.emit(repository.getPishUser(phone))
        }
    }

}