package com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto

data class MaintenanceUpdateDto(
    val author: String?,
    val created_at: String?,
    val id: Int?,
    val publish: Boolean?,
    val publish_locations: List<String>?,
    val translations: List<TranslationDto>?,
    val updated_at: String?
)