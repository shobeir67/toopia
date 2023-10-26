package com.shobeir.toopia.data.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobeir.toopia.data.datastore.PreferenceHelper.MOBILE_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val dataStore: DataStoreRepository
):ViewModel() {
    fun savePhone(phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.saveString(MOBILE_NAME, phone)
        }
    }
    fun saveUsername(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.saveString(MOBILE_NAME, username)
        }
    }

    suspend fun readString(key: Preferences.Key<String>): Flow<String> =
        withContext(Dispatchers.IO) {
            return@withContext dataStore.readString(key)
        }
    suspend fun clearDataStore(){
        viewModelScope.launch {
            dataStore.clearDataStore()
        }
    }

}