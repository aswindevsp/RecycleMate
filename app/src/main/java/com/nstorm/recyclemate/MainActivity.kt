package com.nstorm.recyclemate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.nstorm.recyclemate.ui.navigation.NavigationGraph
import com.nstorm.recyclemate.ui.screens.onboarding.login.LoginScreen
import com.nstorm.recyclemate.ui.theme.RecycleMateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecycleMateTheme {
//                NavigationGraph()
                LoginScreen(viewModel = hiltViewModel())
            }
        }
    }
}

