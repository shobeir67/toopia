package com.shobeir.toopia.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobeir.toopia.data.model.Data
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.repository.HomeRepository
import com.shobeir.toopia.ui.screen.login.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository:HomeRepository) :
    ViewModel() {
    var screenState by mutableStateOf(HomeScreenState.LOGIN_STATE)

    var inputPhoneState by mutableStateOf("")
    var inputCodeState by mutableStateOf("")
    var codeState by mutableStateOf("")
    var loadingState by mutableStateOf(false)

    val loginResponse = MutableStateFlow<NetworkResult<Data>>(NetworkResult.Loading())

    fun sendSms() {
        viewModelScope.launch {
            loadingState = true
            loginResponse.emit(repository.sendSms(inputPhoneState,codeState))
        }
    }


}