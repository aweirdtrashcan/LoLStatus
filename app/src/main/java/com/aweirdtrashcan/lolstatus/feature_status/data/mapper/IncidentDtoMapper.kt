package com.aweirdtrashcan.lolstatus.feature_status.data.mapper

import com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto.IncidentDto
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.Incident

fun IncidentDto.toIncident(): Incident {
    return Incident(
        incident_severity = incident_severity?.toIncidentSeverity(),
        maintenance_status = maintenance_status,
        titles = titles?.map { it.toTitle() } ?: emptyList(),
        incidentUpdates = incidentUpdates?.map { it.toIncidentUpdate() } ?: emptyList()
    )
}