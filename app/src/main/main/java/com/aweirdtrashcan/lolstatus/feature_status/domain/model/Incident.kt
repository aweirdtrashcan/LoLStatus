package com.aweirdtrashcan.lolstatus.feature_status.domain.model

import com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto.IncidentUpdateDto
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.enums.IncidentSeverity

data class Incident(
    val incident_severity: IncidentSeverity?,
    val maintenance_status: String?,
    val titles: List<Title>?,
    val incidentUpdates: List<IncidentUpdate>?
)