package com.aweirdtrashcan.lolstatus.feature_status.data.mapper

import com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto.IncidentUpdateDto
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.IncidentUpdate

fun IncidentUpdateDto.toIncidentUpdate(): IncidentUpdate {
    return IncidentUpdate(
        translations = translations?.map { it.toTranslation() } ?: emptyList(),
    )
}