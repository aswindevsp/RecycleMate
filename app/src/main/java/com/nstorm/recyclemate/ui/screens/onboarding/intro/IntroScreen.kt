package com.nstorm.recyclemate.ui.screens.onboarding.intro

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.nstorm.recyclemate.R

@Composable
fun IntroScreen(
    onNextClick: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        reverseOnRepeat = true
    )

    Box() {
        Column(
            modifier = Modifier
                .padding(40.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Save",
                fontWeight = FontWeight.Bold,
                fontSize = 80.sp
            )
            Text(
                text = "The",
                fontWeight = FontWeight.Bold,
                fontSize = 80.sp
            )
            Text(
                text = "Planet",
                fontWeight = FontWeight.Bold,
                fontSize = 80.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    onNextClick()
                }) {
                Text(text = "Start")
            }
        }
        LottieAnimation(
            modifier = Modifier
                .offset(y = 150.dp),
            composition = composition,
            progress = progress,
        )
    }
}