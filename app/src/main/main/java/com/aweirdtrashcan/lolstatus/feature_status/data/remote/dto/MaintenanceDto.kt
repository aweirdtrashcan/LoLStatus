package com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto

data class MaintenanceDto(
    val archive_at: String?,
    val created_at: String?,
    val id: Int?,
    val incident_severity: String?,
    val maintenance_status: String?,
    val platforms: List<String>?,
    val titles: List<TitleDto>?,
    val updated_at: String?,
    val updates: List<MaintenanceUpdateDto>?
)