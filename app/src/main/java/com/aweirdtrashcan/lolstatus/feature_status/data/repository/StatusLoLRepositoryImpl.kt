package com.aweirdtrashcan.lolstatus.feature_status.data.repository

import com.aweirdtrashcan.lolstatus.feature_status.data.mapper.toStatusResponse
import com.aweirdtrashcan.lolstatus.feature_status.data.remote.ApiRequest
import com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto.StatusResponseDto
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.StatusResponse
import com.aweirdtrashcan.lolstatus.feature_status.domain.repository.StatusLoLRepository
import javax.inject.Inject

class StatusLoLRepositoryImpl @Inject constructor(
    private val api: ApiRequest
): StatusLoLRepository {
    override suspend fun getLolStatus(apikey: String): StatusResponse {
        val apiResponse: StatusResponseDto = api.getLolStatus(apikey)
        return apiResponse.toStatusResponse()
    }
}