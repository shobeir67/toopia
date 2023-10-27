package com.shobeir.toopia.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.shobeir.toopia.data.datastore.DataStoreRepository
import com.shobeir.toopia.data.datastore.DataStoreRepositoryImpl
import com.shobeir.toopia.data.datastore.USER_PREFERENCES_NAME
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME,
    )
    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(context.dataStore)
}