package com.example.gshop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.gshop.ui.screens.onboarding.login.Login
import com.example.gshop.ui.screens.onboarding.otp.Otp

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.OnBoarding.routeTemplate,
    ) {
        onBoardingGraph(navController)
        //homeGraph(navController)
    }

}

private fun NavGraphBuilder.onBoardingGraph(
    navController: NavController
) {
    navigation(
        startDestination = Destinations.Login.createRoute(),
        route = Destinations.OnBoarding.routeTemplate
    ) {

        onBoardingLogin(navController)
        onBoardingOtp(navController)
    }
}


// Individual level screen destinations
private fun NavGraphBuilder.onBoardingLogin(
    navController: NavController
) {
    composable(Destinations.Login.routeTemplate) {
        Login(
            viewModel = hiltViewModel(),
            navigateToOtp = {
                navController.navigate(Destinations.Otp.createRoute())
            }
        )
    }
}

private fun NavGraphBuilder.onBoardingOtp(
    navController: NavController
) {
    composable(Destinations.Otp.routeTemplate) {
        Otp(
            navigateUp = {
                navController.popBackStack()
            }
        )
    }
}


