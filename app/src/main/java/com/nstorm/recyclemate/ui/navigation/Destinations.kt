package com.nstorm.recyclemate.ui.navigation

sealed class Destinations(val routeTemplate: String) {
    sealed class NoArgs(route: String) : Destinations(route) {
        fun createRoute() = routeTemplate
    }
    sealed class TopLevelDestination(route: String) : Destinations(route)


    data object OnBoarding : TopLevelDestination("onBoarding")
    data object HomePages: TopLevelDestination("homePages")

    data object Login : NoArgs("onBoarding/login")
    data object Intro: NoArgs("onBoarding/intro")
    data object Home: NoArgs("homePages/home")


    data object Plastic: NoArgs("homePages/plastic")
    data object Paper: NoArgs("homePages/paper")
    data object Metal: NoArgs("homePages/leather")
    data object Glass: NoArgs("homePages/glass")



}