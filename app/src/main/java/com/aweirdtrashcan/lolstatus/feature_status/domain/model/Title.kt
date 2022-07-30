package com.aweirdtrashcan.lolstatus.feature_status.domain.model

import com.aweirdtrashcan.lolstatus.feature_status.domain.model.enums.Locales

data class Title(
    val content: String = "",
    val locale: Locales = Locales.NULL
)