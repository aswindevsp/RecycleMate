package com.nstorm.recyclemate.ui.screens.onboarding.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nstorm.recyclemate.ui.screens.onboarding.OnboardingViewModel
import com.nstorm.recyclemate.R

private infix fun String.onValueChange(any: Any) {

}

@Composable
fun LoginScreen(viewModel: OnboardingViewModel) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box(
            modifier = Modifier
                    .fillMaxSize()
                        .paint(painter = painterResource(id = R.drawable.recycle),
                                contentScale = ContentScale.Crop
                        )

    ){

        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 550.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Enter Username",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)
            TextField(
                    value = name,
                    onValueChange = { name = it },
                    singleLine = true,
                    placeholder = { Text("Username") }
            )
            Text(text = "Password",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)
            TextField(
                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    placeholder = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation()
            )
            Button(onClick = {

            }) {
                    Text("Login")

            }
        }
    }
}

