package com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status

import com.aweirdtrashcan.lolstatus.feature_status.domain.model.Incident
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.Maintenance

data class ScreenLoLStatusState(
    val incidents: List<Incident> = emptyList(),
    val maintenances: List<Maintenance> = emptyList(),
    val shouldShow: Boolean = false,
    val errorMessage: String? = null,
    val showError: Boolean = false
)
