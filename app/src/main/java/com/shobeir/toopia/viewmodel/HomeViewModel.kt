package com.shobeir.toopia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobeir.toopia.data.model.ModelTeam
import com.shobeir.toopia.data.model.Slider
import com.shobeir.toopia.data.model.User
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel@Inject constructor(private val repository: HomeRepository) :
    ViewModel() {

    val teamResponse = MutableStateFlow<NetworkResult<ModelTeam>>(NetworkResult.Loading())
    val winnerResponse = MutableStateFlow<NetworkResult<User>>(NetworkResult.Loading())
    val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())

    fun getAllData(){
        viewModelScope.launch {

            launch {
                slider.emit(repository.getSlider())
            }

            launch {
                teamResponse.emit(repository.getTeam())
            }

            launch {
                winnerResponse.emit(repository.getWinner())
            }

        }
    }
}