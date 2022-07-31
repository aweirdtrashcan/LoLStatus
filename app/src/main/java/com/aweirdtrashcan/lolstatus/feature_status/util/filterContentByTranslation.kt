package com.aweirdtrashcan.lolstatus.feature_status.presentation.util

import com.aweirdtrashcan.lolstatus.feature_status.domain.model.Translation
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.enums.Locales

fun filterContentByTranslation(translations: List<Translation>): String {
    var translationContent: String = ""
    for (translation in translations) {
        if (translation.locale == Locales.PT_BR) {
            translationContent = translation.content
        }
    }
    return translationContent
}
