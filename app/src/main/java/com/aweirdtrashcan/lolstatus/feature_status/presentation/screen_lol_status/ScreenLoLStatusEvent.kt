package com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status

sealed class ScreenLoLStatusEvent {
    object ApiSuccess: ScreenLoLStatusEvent()
    data class ApiError(val error: String? = null): ScreenLoLStatusEvent()
    data class ApiLoading(var isLoading: Boolean): ScreenLoLStatusEvent()
}