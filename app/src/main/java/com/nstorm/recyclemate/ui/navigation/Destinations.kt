package com.example.gshop.ui.navigation

sealed class Destinations(val routeTemplate: String) {
    sealed class NoArgs(route: String) : Destinations(route) {
        fun createRoute() = routeTemplate
    }
    sealed class TopLevelDestination(route: String) : Destinations(route)


    data object OnBoarding : TopLevelDestination("onBoarding")
    data object Login : NoArgs("onBoarding/login")
    data object Otp : NoArgs("onBoarding/otp")

}