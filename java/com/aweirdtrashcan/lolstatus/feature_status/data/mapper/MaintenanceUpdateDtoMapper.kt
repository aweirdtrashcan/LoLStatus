package com.aweirdtrashcan.lolstatus.feature_status.data.mapper

import com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto.MaintenanceUpdateDto
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.MaintenanceUpdate

fun MaintenanceUpdateDto.toMaintenanceUpdate(): MaintenanceUpdate {
    return MaintenanceUpdate(
        translations = translations?.map { it.toTranslation() } ?: emptyList()
    )
}