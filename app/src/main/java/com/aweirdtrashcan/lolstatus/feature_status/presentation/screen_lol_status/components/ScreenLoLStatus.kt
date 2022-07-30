package com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status.components

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.enums.Locales
import com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status.ScreenLoLStatusEvent
import com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status.ScreenLoLStatusViewModel
import kotlinx.coroutines.flow.collectLatest



@Composable
fun ScreenLoLStatus(
    viewModel: ScreenLoLStatusViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val scope = rememberCoroutineScope()
    var shouldShow by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    var theresContentIncident by remember {
        mutableStateOf(false)
    }
    var animatedVisibilityIncident by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collectLatest { event ->
            when (event) {
                is ScreenLoLStatusEvent.ApiSuccess -> {
                    shouldShow = true
                }
                is ScreenLoLStatusEvent.ApiError -> {

                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxHeight(.50f)
                    .fillMaxWidth()
                    .shadow(8.dp, RectangleShape)
            ) {

            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier
                    .fillMaxHeight(100f)
                    .fillMaxWidth()
                    .shadow(8.dp, RectangleShape)
            ) {
                if (shouldShow) {
                    if (state.incidents.isNotEmpty()) {
                        LazyColumn {
                            items(state.incidents.size) { listIndex ->
                                val currTitle = state.incidents[listIndex]
                                val currContent = state.incidents[listIndex]
                                if (currTitle.titles != null && currContent.titles != null) {
                                    for (index in currTitle.titles.indices) {
                                        val title = currContent.titles[index]
                                        if (title.locale == Locales.PT_BR) {
                                            Box(modifier = Modifier.fillMaxWidth()) {
                                                Text(text = title.content)
                                                Spacer(modifier = Modifier.height(10.dp))
                                                if (theresContentIncident) {
                                                    Text(text = "testing123")
                                                }
                                            }
                                        }
                                        val incidentUpdates = currContent.incidentUpdates
                                        if (incidentUpdates != null) {
                                            if (incidentUpdates.isNotEmpty()) {
                                                theresContentIncident = true
                                                for (thisIncident in incidentUpdates) {
                                                    println("-------THIS INCIDENT $thisIncident---------")
                                                    for (translation in thisIncident.translations) {
                                                        showToast(translation.toString(), context)
                                                        if (translation.locale == Locales.PT_BR) {
                                                            showToast("Problem with text", context)
                                                            Text(text = translation.content)
                                                        } else {
                                                            showToast("Problem with locale", context)
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            showToast("IncidentUpdate is null", context)
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Text(text = "Nenhum incidente reportado! =)")
                    }
                }
            }
        }
    }
}

fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}