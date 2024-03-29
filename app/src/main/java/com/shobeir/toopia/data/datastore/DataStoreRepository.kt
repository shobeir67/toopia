package com.shobeir.toopia.data.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun readString(key: Preferences.Key<String>): Flow<String>
    suspend fun saveString(key: Preferences.Key<String>, name: String)
    suspend fun readBoolean(key: Preferences.Key<Boolean>, name: String) : Flow<Boolean>
    suspend fun saveBoolean(key: Preferences.Key<Boolean>, isChecked: Boolean)
    suspend fun readInt(key: Preferences.Key<Int>, value: Int) : Flow<Int>
    suspend fun saveInt(key: Preferences.Key<Int>, value: Int)
    suspend fun readFloat(key: Preferences.Key<Float>, value: Float) : Flow<Float>
    suspend fun saveFloat(key: Preferences.Key<Float>, value: Float)
    suspend fun readLong(key: Preferences.Key<Long>, value: Long) : Flow<Long>
    suspend fun saveLong(key: Preferences.Key<Long>, value: Long)
    suspend fun removeKeyFromDataStore(key: Preferences.Key<Any>)
    suspend fun clearDataStore()

}