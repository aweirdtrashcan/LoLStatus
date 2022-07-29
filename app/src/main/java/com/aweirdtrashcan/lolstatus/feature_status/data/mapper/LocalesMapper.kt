package com.aweirdtrashcan.lolstatus.feature_status.data.mapper

import com.aweirdtrashcan.lolstatus.feature_status.domain.model.enums.Locales

fun String.toLocales(): Locales {
    return when {
        this.lowercase().contains("pt_br") -> Locales.PT_BR
        this.lowercase().contains("en_us") -> Locales.EN_US
        else ->  Locales.NULL
    }
}