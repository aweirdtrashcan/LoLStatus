package com.aweirdtrashcan.lolstatus.feature_status.data.remote

import com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto.StatusResponseDto
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.StatusResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    @GET("?api_key")
    suspend fun getLolStatus(
        @Query("api_key") apiKey: String
    ): StatusResponseDto

}