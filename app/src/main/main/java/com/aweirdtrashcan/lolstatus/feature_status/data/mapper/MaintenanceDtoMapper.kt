package com.aweirdtrashcan.lolstatus.feature_status.data.mapper

import com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto.MaintenanceDto
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.Maintenance

fun MaintenanceDto.toMaintenance(): Maintenance {
    return Maintenance(
        incident_severity = incident_severity?.toIncidentSeverity(),
        maintenance_status = maintenance_status,
        titles = titles?.map { it.toTitle() } ?: emptyList(),
        updates = updates?.map { it.toMaintenanceUpdate() } ?: emptyList()
    )
}