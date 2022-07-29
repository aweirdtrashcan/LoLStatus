package com.aweirdtrashcan.lolstatus.feature_status.data.mapper

import com.aweirdtrashcan.lolstatus.feature_status.domain.model.enums.IncidentSeverity

fun String.toIncidentSeverity(): IncidentSeverity {
    return when {
        this.lowercase().contains("info") -> {
            IncidentSeverity.INFO
        }
        this.lowercase().contains("warning") -> {
            IncidentSeverity.WARNING
        }
        this.lowercase().contains("critical") -> {
            IncidentSeverity.CRITICAL
        }
        else -> IncidentSeverity.NULL
    }
}