package com.aweirdtrashcan.lolstatus.feature_status.domain.model

import com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto.MaintenanceUpdateDto
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.enums.IncidentSeverity

data class Maintenance(
    val incident_severity: IncidentSeverity?,
    val maintenance_status: String?,
    val titles: List<Title> = emptyList(),
    val updates: List<MaintenanceUpdate>?
)