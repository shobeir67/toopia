package com.shobeir.toopia.ui.screen.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobeir.toopia.data.model.City
import com.shobeir.toopia.data.model.Store
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShopViewModel@Inject constructor(private val repository: HomeRepository) :
    ViewModel() {

    val store = MutableStateFlow<NetworkResult<List<Store>>>(NetworkResult.Loading())
    val city = MutableStateFlow<NetworkResult<List<City>>>(NetworkResult.Loading())
    val storeUser = MutableStateFlow<NetworkResult<Store>>(NetworkResult.Loading())
    fun getAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            launch {
               store.emit(repository.getAllShoping())
            }

        }
    }
    fun getCity(){
        viewModelScope.launch(Dispatchers.IO) {
            city.emit(repository.getCity())
        }
    }

    fun getStoreUser(phone:String){
        viewModelScope.launch(Dispatchers.IO) {
            storeUser.emit(repository.getStoreUser(phone))
        }
    }

}