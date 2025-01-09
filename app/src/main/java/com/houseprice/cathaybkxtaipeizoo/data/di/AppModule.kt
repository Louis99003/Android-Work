package com.houseprice.cathaybkxtaipeizoo.data.di

import com.houseprice.cathaybkxtaipeizoo.BuildConfig
import com.houseprice.cathaybkxtaipeizoo.data.ApiService
import com.houseprice.cathaybkxtaipeizoo.data.remote.RemoteRepository
import com.houseprice.cathaybkxtaipeizoo.data.remote.RemoteRepositoryImpl
import com.houseprice.cathaybkxtaipeizoo.data.usecase.UseCaseRepository
import com.houseprice.cathaybkxtaipeizoo.data.usecase.UseCaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(remoteRepository: RemoteRepositoryImpl): RemoteRepository {
        return remoteRepository
    }

    @Provides
    @Singleton
    fun provideUseCaseRepository(useCaseRepository: UseCaseRepositoryImpl): UseCaseRepository {
        return useCaseRepository
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return Retrofit.Builder().baseUrl(BuildConfig.API_MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient.build()).build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}