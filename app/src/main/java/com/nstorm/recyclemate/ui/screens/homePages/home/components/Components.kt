package com.nstorm.recyclemate.ui.screens.homePages.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun NewsCard(image: Int, description: String) {
    Card(modifier = Modifier.size(100.dp)) {
        Image(painter = painterResource(id = image), contentDescription = "")
        Text(text = description)
    }
}