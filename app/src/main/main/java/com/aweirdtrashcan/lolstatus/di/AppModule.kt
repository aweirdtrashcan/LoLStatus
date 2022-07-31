package com.aweirdtrashcan.lolstatus.di

import com.aweirdtrashcan.lolstatus.feature_status.data.remote.ApiRequest
import com.aweirdtrashcan.lolstatus.feature_status.data.repository.StatusLoLRepositoryImpl
import com.aweirdtrashcan.lolstatus.feature_status.domain.repository.StatusLoLRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiRequest(): ApiRequest {
        return Retrofit.Builder()
            .baseUrl("https://br1.api.riotgames.com/lol/status/v4/platform-data/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)
    }

    @Provides
    @Singleton
    fun provideStatusLoLRepository(api: ApiRequest): StatusLoLRepository {
        return StatusLoLRepositoryImpl(api)
    }

}