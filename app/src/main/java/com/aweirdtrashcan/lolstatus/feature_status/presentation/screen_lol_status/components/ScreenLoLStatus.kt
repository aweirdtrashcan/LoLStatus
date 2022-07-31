package com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status.components

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.Title
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.Translation
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.enums.IncidentSeverity
import com.aweirdtrashcan.lolstatus.feature_status.domain.model.enums.Locales
import com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status.ScreenLoLStatusViewModel

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

private fun filterTitleByLocation(titles: List<Title>?): String {
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

private fun filterContentByTranslation(translations: List<Translation>): String {
    var translationContent: String = ""
    for (translation in translations) {
        if (translation.locale == Locales.PT_BR) {
            translationContent = translation.content
        }
    }
    return translationContent
}

@Composable
private fun contentIncidentToColor(
    incident_severity: IncidentSeverity?
): Color {
    return when (incident_severity) {
        IncidentSeverity.INFO -> {
            MaterialTheme.colors.background
        }
        IncidentSeverity.WARNING -> {
            Color(0xFFFF8761)
        }
        IncidentSeverity.CRITICAL -> {
            Color.Red
        }
        IncidentSeverity.NULL -> {
            MaterialTheme.colors.background
        }
        else -> {
            MaterialTheme.colors.background
        }
    }
}

@Composable
private fun CustomText(
    statusTitle: String,
    fontWeight: FontWeight,
    fontSize: Int
) {
    Text(
        text = statusTitle,
        fontWeight = fontWeight,
        fontSize = fontSize.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
private fun ShowList(
    titleString: String,
    theresContentBoolean: Boolean,
    contentString: String,
    statusTitle: String,
    backgroundColor: Color = MaterialTheme.colors.background
) {
    Card(
        // Small offset to give space to the ShowListTitle() function
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .offset(y = 10.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = backgroundColor
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