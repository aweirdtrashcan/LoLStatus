package com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.enums.IncidentSeverity

@Composable
fun contentIncidentToColor(
    incident_severity: IncidentSeverity?
): Color {
    return when (incident_severity) {
        IncidentSeverity.INFO -> {
            MaterialTheme.colors.background
        }
        IncidentSeverity.WARNING -> {
            Color(0xFFFF8761)
        }
        IncidentSeverity.CRITICAL -> {
            Color.Red
        }
        IncidentSeverity.NULL -> {
            MaterialTheme.colors.background
        }
        else -> {
            MaterialTheme.colors.background
        }
    }
}