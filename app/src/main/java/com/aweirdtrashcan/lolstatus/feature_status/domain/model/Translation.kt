package com.aweirdtrashcan.lolstatus.feature_status.domain.model

import com.aweirdtrashcan.lolstatus.feature_status.domain.model.enums.Locales

data class Translation(
    val content: String,
    val locale: Locales
)