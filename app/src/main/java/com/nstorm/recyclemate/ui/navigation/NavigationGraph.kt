package com.nstorm.recyclemate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.gshop.ui.navigation.Destinations
import com.nstorm.recyclemate.ui.screens.homePages.home.HomeScreen
import com.nstorm.recyclemate.ui.screens.onboarding.intro.IntroScreen
import com.nstorm.recyclemate.ui.screens.onboarding.login.LoginScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = Destinations.OnBoarding.routeTemplate,
    ) {
        onBoardingGraph(navController)
        homePagesGraph(navController)
    }

}

private fun NavGraphBuilder.onBoardingGraph(
    navController: NavController
) {
    navigation(
        startDestination = Destinations.Login.createRoute(),
        route = Destinations.OnBoarding.routeTemplate
    ) {
        onBoardingIntro(navController)
        onBoardingLogin(navController)
    }
}

private fun NavGraphBuilder.homePagesGraph(
    navController: NavController
) {
    navigation(
        startDestination = Destinations.Home.createRoute(),
        route = Destinations.HomePages.routeTemplate
    ) {
        homePage(navController)
    }
}


// Individual level screen destinations
private fun NavGraphBuilder.onBoardingLogin(
    navController: NavController
) {
    composable(Destinations.Login.routeTemplate) {
        LoginScreen(
            viewModel = hiltViewModel(),
        )
    }
}

private fun NavGraphBuilder.onBoardingIntro(navController: NavController) {
    composable(Destinations.Intro.routeTemplate) {
        IntroScreen()
    }
}


private fun NavGraphBuilder.homePage(
    navController: NavController
) {
    composable(Destinations.Home.routeTemplate) {
        HomeScreen()
    }
}
