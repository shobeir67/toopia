package com.shobeir.toopia.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.shobeir.toopia.data.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreRepositoryImpl
@Inject constructor(private var dataStore: DataStore<Preferences>) : DataStoreRepository {

    override suspend fun readString(key: Preferences.Key<String>): Flow<String> {
        return dataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val showCompleted = preferences[key] ?: "null"
                showCompleted
            }
    }

    override suspend fun saveString(key: Preferences.Key<String>, name: String) {
        dataStore.edit { preferences ->
            preferences[key] = name
        }
    }

    override suspend fun readBoolean(key: Preferences.Key<Boolean>, name: String): Flow<Boolean> {
        return dataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val isChecked = preferences[key] ?: false
                isChecked
            }
    }

    override suspend fun saveBoolean(key: Preferences.Key<Boolean>, isChecked: Boolean) {
        dataStore.edit { preferences ->
            preferences[key] = isChecked
        }
    }

    override suspend fun readInt(key: Preferences.Key<Int>, value: Int): Flow<Int> {
        return dataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val isChecked = preferences[key] ?: -1
                isChecked
            }
    }

    override suspend fun saveInt(key: Preferences.Key<Int>, value: Int) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun readFloat(key: Preferences.Key<Float>, value: Float): Flow<Float> {
        return dataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val isChecked = preferences[key] ?: 0f
                isChecked
            }
    }

    override suspend fun saveFloat(key: Preferences.Key<Float>, value: Float) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun readLong(key: Preferences.Key<Long>, value: Long): Flow<Long> {
        return dataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(emptyPreferences())
                } else throw ex
            }
            .map { preferences ->
                val isChecked = preferences[key] ?: 0
                isChecked
            }
    }

    override suspend fun saveLong(key: Preferences.Key<Long>, value: Long) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun removeKeyFromDataStore(key: Preferences.Key<Any>) {
        TODO("Not yet implemented")
    }

    override suspend fun clearDataStore() {
       dataStore.edit {preferences ->
           preferences.clear()
       }
    }

}