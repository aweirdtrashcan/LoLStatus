package com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status

sealed class ScreenLoLStatusEvent {
    object ApiSuccess: ScreenLoLStatusEvent()
    object ApiError: ScreenLoLStatusEvent()
}