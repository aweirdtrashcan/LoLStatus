package com.aweirdtrashcan.lolstatus.feature_status.presentation.util

import com.aweirdtrashcan.lolstatus.feature_status.domain.model.Title
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.enums.Locales

fun filterTitleByLocation(titles: List<Title>?): String {
    var contentLang: String = "Nenhuma atualização foi encontrada."
    if (titles != null) {
        for (title in titles) {
            if (title.locale == Locales.PT_BR) {
                contentLang = title.content
            }
        }
    }
    return contentLang
}