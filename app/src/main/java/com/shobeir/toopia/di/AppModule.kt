package com.shobeir.toopia.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.shobeir.toopia.data.datastore.DataStoreRepository
import com.shobeir.toopia.data.datastore.DataStoreRepositoryImpl
import com.shobeir.toopia.data.datastore.USER_PREFERENCES_NAME
import com.shobeir.toopia.data.remote.ApiService
import com.shobeir.toopia.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpClient ():OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60,TimeUnit.SECONDS)
            .writeTimeout(60,TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
         Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
             .client(okHttpClient)
            .build()


    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ApiService =
         retrofit.create(ApiService::class.java)


    val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME,
    )

    @Singleton
    @Provides
    fun providePreferenceStorage(@ApplicationContext context: Context): DataStoreRepository =
        DataStoreRepositoryImpl(context.dataStore)

}