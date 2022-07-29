package com.aweirdtrashcan.lolstatus.feature_status.data.mapper

import com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto.TitleDto
import com.aweirdtrashcan.lolstatus.feature_status.data.remote.dto.TranslationDto
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.Title
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.Translation
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.enums.Locales

fun TitleDto.toTitle(): Title {
    return Title(
        content = content,
        locale = when {
            locale.lowercase().contains("pt_br") -> Locales.PT_BR
            locale.lowercase().contains("en_us") -> Locales.EN_US
            else -> Locales.NULL
        }
    )
}

fun TranslationDto.toTranslation(): Translation {
    return Translation(
        content = content,
        locale = when {
            locale.lowercase().contains("pt_br") -> Locales.PT_BR
            locale.lowercase().contains("en_us") -> Locales.EN_US
            else -> Locales.NULL
        }
    )
}

