package com.example.projectmanager.ui.theme.navigation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object LogInScreen: Screen("Signup_screen")
}
