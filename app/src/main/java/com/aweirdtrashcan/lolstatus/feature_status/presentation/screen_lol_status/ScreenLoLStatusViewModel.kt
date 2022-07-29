package com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.Incident
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.Maintenance
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.StatusResponse
import com.aweirdtrashcan.lolstatus.feature_status.domain.repository.StatusLoLRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ScreenLoLStatusViewModel @Inject constructor(
    private val repository: StatusLoLRepository
) : ViewModel() {

    companion object {
        init {
            System.loadLibrary("lolstatus")
        }
    }

    private external fun getApiKey(): String

    var state by mutableStateOf(ScreenLoLStatusState())
        private set

    val sharedFlow= MutableSharedFlow<ScreenLoLStatusEvent>()

    init {
        getLolStatus(getApiKey())
    }

    private fun getLolStatus(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getLolStatus(apiKey)
                state = state.copy(
                    incidents = response.incidents,
                    maintenances = response.maintenances,
                )
                println(state)
                sharedFlow.emit(ScreenLoLStatusEvent.ApiSuccess)
            } catch (e: HttpException) {
                Log.e("HTTP ERROR:", e.message!!)
                sharedFlow.emit(ScreenLoLStatusEvent.ApiError)
            }
        }
    }
}