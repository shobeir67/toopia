package com.shobeir.toopia.ui.screen.karbordi.nobateman

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobeir.toopia.data.model.Data
import com.shobeir.toopia.data.model.News
import com.shobeir.toopia.data.model.nobatdehi.ModelZaman
import com.shobeir.toopia.data.remote.HomeApiInterface
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ViewModelNobat @Inject constructor(
    private val apiService: HomeApiInterface,
    private val repository: HomeRepository
):ViewModel(){
    val listSanse = MutableStateFlow<ModelZaman?>(null)
    val postError = MutableStateFlow<String?>("")
    val loading = MutableStateFlow(false)
    val status = MutableStateFlow<String?>("")
    val priceUser = MutableStateFlow<NetworkResult<Data>>(NetworkResult.Loading())


    fun getSanse(){
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.getSanse()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let { body ->
                        listSanse.value = body
                        loading.value = false
                    } } else {
                    postError.value = response.message()
                    loading.value = false
                }
            }
        }
    }

    fun addNobat(code:String,mobile:String,zaman:String){
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.addNobat(code,mobile,zaman)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let { body ->
                        status.value = body.status
                        loading.value = false
                    } } else {
                    postError.value = response.message()
                    loading.value = false
                }
            }
        }
    }

    fun setPriceUserNobat(phone:String){
        viewModelScope.launch(Dispatchers.IO){
            priceUser.emit(repository.setPriceUserNobat(phone))
        }
    }
}


