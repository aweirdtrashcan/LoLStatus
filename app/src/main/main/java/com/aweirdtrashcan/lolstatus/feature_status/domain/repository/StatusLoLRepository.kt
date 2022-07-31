package com.aweirdtrashcan.lolstatus.feature_status.domain.repository

import com.aweirdtrashcan.lolstatus.feature_status.domain.model.StatusResponse

interface StatusLoLRepository {

    suspend fun getLolStatus(apikey: String): StatusResponse

}