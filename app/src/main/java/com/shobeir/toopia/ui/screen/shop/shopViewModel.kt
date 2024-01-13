package com.shobeir.toopia.ui.screen.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobeir.toopia.data.model.ModelTeam
import com.shobeir.toopia.data.model.News
import com.shobeir.toopia.data.model.Shoping
import com.shobeir.toopia.data.model.Slider
import com.shobeir.toopia.data.model.User
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class shopViewModel@Inject constructor(private val repository: HomeRepository) :
    ViewModel() {

    val shoping = MutableStateFlow<NetworkResult<List<Shoping>>>(NetworkResult.Loading())
    fun getAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            launch {
               shoping.emit(repository.getAllShoping())
            }

        }
    }
}