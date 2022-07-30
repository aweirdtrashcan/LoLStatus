package com.aweirdtrashcan.lolstatus.feature_status.domain.model

data class StatusResponse(
    val incidents: List<Incident> = emptyList(),
    val maintenances: List<Maintenance> = emptyList(),
)