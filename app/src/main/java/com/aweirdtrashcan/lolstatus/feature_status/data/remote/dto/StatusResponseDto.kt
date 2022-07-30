package com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto

data class StatusResponseDto(
    val id: String?,
    val incidents: List<IncidentDto>?,
    val locales: List<String>?,
    val maintenances: List<MaintenanceDto>?,
    val name: String?
)