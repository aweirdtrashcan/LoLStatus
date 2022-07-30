package com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status.components

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
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

    var titleString: String = ""
    var contentString: String = ""
    var theresContentBoolean: Boolean = false

    val state = viewModel.state
    val context = LocalContext.current
    val activity = LocalContext.current as? Activity
//    LaunchedEffect(key1 = true) {
//        viewModel.sharedFlow.collect { event ->
//            when (event) {
//                is ScreenLoLStatusEvent.ApiSuccess -> {
//                    //TODO
//                }
//                is ScreenLoLStatusEvent.ApiError -> {
//                }
//            }
//        }
//    }

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
                if (!state.showError) {
                    if (state.shouldShow) {
                        if (state.incidents.isNotEmpty()) {
                            LazyColumn {
                                items(state.incidents.size) { listIndex ->
                                    val currTitle = state.incidents[listIndex]
                                    val currContent = state.incidents[listIndex]
                                    if (currTitle.titles != null && currContent.titles != null) {
                                        for (index in currTitle.titles.indices) {
                                            val title = currContent.titles[index]
                                            if (title.locale == Locales.PT_BR) {
                                                titleString = title.content

                                            }
                                            val incidentUpdates = currContent.incidentUpdates
                                            if (incidentUpdates != null) {
                                                if (incidentUpdates.isNotEmpty()) {
                                                    theresContentBoolean = true
                                                    for (thisIncident in incidentUpdates) {
                                                        for (translation in thisIncident.translations) {
                                                            if (translation.locale == Locales.PT_BR) {
                                                                contentString = translation.content
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                showToast("IncidentUpdate is null", context)
                                            }
                                        }
                                    }
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        shape = RoundedCornerShape(10.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Text(
                                                modifier = Modifier.fillMaxWidth(),
                                                text = titleString,
                                                style = MaterialTheme.typography.body1,
                                                textAlign = TextAlign.Center
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                            if (theresContentBoolean) {
                                                Text(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(10.dp),
                                                    text = contentString,
                                                    style = MaterialTheme.typography.body2
                                                )
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
                if (state.showError) {
                    AlertDialog(
                        onDismissRequest = {
                            activity?.finish()
                        },
                        title = {
                            Text(text = "Oops... Ocorreu um erro")
                        },
                        text = {
                            Text(text = "Houve uma falha ao conectar-se a Internet. Por favor, verifique sua conexão! Erro: ${state.errorMessage}")
                        },
                        confirmButton = {
                            Button(onClick = {
                                activity?.finish()
                            }) {
                                Text(text = "Fechar App")
                            }
                        }
                    )
                }
            }
        }
    }
}

fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}