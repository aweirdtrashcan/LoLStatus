package com.aweirdtrashcan.lolstatus.feature_status.data.mapper

import com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto.StatusResponseDto
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.StatusResponse

fun StatusResponseDto.toStatusResponse(): StatusResponse {
    return StatusResponse(
        incidents = incidents?.map { it.toIncident() } ?: emptyList(),
        maintenances = maintenances?.map { it.toMaintenance() } ?: emptyList(),
    )
}