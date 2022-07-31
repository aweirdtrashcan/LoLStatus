package com.aweirdtrashcan.lolstatus.feature_status.presentation.screen_lol_status.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ShowList(
    titleString: String,
    theresContentBoolean: Boolean,
    contentString: String,
    statusTitle: String,
    backgroundColor: Color = MaterialTheme.colors.background
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(10.dp, RectangleShape),
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