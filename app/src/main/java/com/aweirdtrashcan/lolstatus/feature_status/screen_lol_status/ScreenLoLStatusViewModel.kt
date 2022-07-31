package com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aweirdtrashcan.lolstatus.feature_status.domain.repository.StatusLoLRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import okio.IOException
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
        getLolStatus()
    }

    fun getLolStatus() {
        viewModelScope.launch {
            sharedFlow.emit(ScreenLoLStatusEvent.ApiLoading(true))
            try {
                val response = repository.getLolStatus(getApiKey())
                state = state.copy(
                    incidents = response.incidents,
                    maintenances = response.maintenances,
                    shouldShow = true
                )
                sharedFlow.emit(ScreenLoLStatusEvent.ApiLoading(false))
                println(state)
                sharedFlow.emit(ScreenLoLStatusEvent.ApiSuccess)
            } catch (e: HttpException) {
                afterCatch(e.message)
            } catch (e: IOException) {
                afterCatch(e.message)
            }
        }
    }

    private fun afterCatch(e: String?) {
        viewModelScope.launch {
            state = state.copy(shouldShow = false, showError = true, errorMessage = e)
            sharedFlow.emit(ScreenLoLStatusEvent.ApiLoading(false))
            println(e)
        }
    }
}