package com.nstorm.recyclemate.ui.screens.onboarding.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IntroScreen(
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(40.dp)
    ) {

        Button(onClick = {
            onNextClick()
        }) {
            Text(text = "Hello World")
        }
    }
}