package com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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

    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collectLatest { event ->
            when(event) {
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
                            items(state.incidents.size) { index ->
                                val currItem = state.incidents[index]
                            }
                        }
                    }
                }
            }
        }
    }
}