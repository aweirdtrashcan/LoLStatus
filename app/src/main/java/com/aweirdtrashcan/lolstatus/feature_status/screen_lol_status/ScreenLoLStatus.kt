package com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status.components.*
import com.aweirdtrashcan.lolstatus.feature_status.presentation.util.filterContentByTranslation
import com.aweirdtrashcan.lolstatus.feature_status.presentation.util.filterTitleByLocation

@Composable
fun ScreenLoLStatus(
    viewModel: ScreenLoLStatusViewModel = hiltViewModel()
) {

    var titleString: String
    var contentString: String = ""
    var theresContentBoolean: Boolean = false
    var mColor: Color

    val state = viewModel.state
    val activity = LocalContext.current as? Activity

    ComposableLifecycle { _, event ->
        when(event) {
            Lifecycle.Event.ON_PAUSE -> {
                viewModel.getLolStatus()
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
                    .shadow(8.dp, RectangleShape)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxHeight(.50f)
                        .fillMaxWidth()
                ) {
                    CustomText(
                        statusTitle = "Manutenções",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18
                    )
                    if (!state.showError && state.shouldShow && state.maintenances.isNotEmpty()) {
                        LazyColumn {
                            items(state.maintenances.size) { listIndex ->
                                val currContent = state.maintenances[listIndex]

                                val color = contentIncidentToColor(
                                    currContent.incident_severity
                                )

                                titleString = filterTitleByLocation(currContent.titles)

                                val maintenanceUpdates = currContent.updates
                                if (maintenanceUpdates != null) {
                                    if (maintenanceUpdates.isNotEmpty()) {
                                        theresContentBoolean = true
                                        for (thisMaintenance in maintenanceUpdates) {
                                            contentString =
                                                filterContentByTranslation(thisMaintenance.translations)
                                        }
                                    }
                                }
                                ShowList(
                                    titleString = titleString,
                                    theresContentBoolean = theresContentBoolean,
                                    contentString = contentString,
                                    statusTitle = "Manutenções",
                                    backgroundColor = color
                                )
                            }
                        }
                    } else {
                        CustomText(
                            statusTitle = "Nenhuma Manutenção Reportada!",
                            fontWeight = FontWeight.Normal,
                            fontSize = 14
                        )
                    }


                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .shadow(8.dp, RectangleShape)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(100f)
                ) {
                    CustomText(
                        statusTitle = "Incidentes",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18
                    )

                    if (state.shouldShow && state.incidents.isNotEmpty() && !state.showError) {
                        LazyColumn {
                            items(state.incidents.size) { listIndex ->
                                val currContent = state.incidents[listIndex]

                                mColor = contentIncidentToColor(
                                    currContent.incident_severity
                                )

                                titleString = filterTitleByLocation(currContent.titles)

                                val incidentUpdates = currContent.incidentUpdates
                                if (incidentUpdates != null) {
                                    if (incidentUpdates.isNotEmpty()) {
                                        theresContentBoolean = true
                                        for (thisIncident in incidentUpdates) {
                                            contentString =
                                                filterContentByTranslation(thisIncident.translations)
                                        }
                                    }
                                }
                                ShowList(
                                    titleString = titleString,
                                    theresContentBoolean = theresContentBoolean,
                                    contentString = contentString,
                                    statusTitle = "Incidentes",
                                    backgroundColor = mColor
                                )
                            }
                        }
                    } else {
                        CustomText(
                            statusTitle = "Nenhum Incidente Reportado!",
                            fontWeight = FontWeight.Normal,
                            fontSize = 14
                        )
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

@Composable
fun ComposableLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            onEvent(source, event)
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
