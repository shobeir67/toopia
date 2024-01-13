package com.shobeir.toopia.ui.screen.news.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobeir.toopia.data.remote.NetworkResult
import com.shobeir.toopia.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel@Inject constructor(private val repository: HomeRepository) :
    ViewModel() {

    val imageNews = MutableStateFlow<NetworkResult<List<ImagePost>>>(NetworkResult.Loading())

    fun getAllImageNews(code:String){
        viewModelScope.launch {
            imageNews.emit(repository.getAllNews(code))
        }
    }
}