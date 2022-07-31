package com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
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