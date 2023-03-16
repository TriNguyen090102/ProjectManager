package com.example.projectmanager.ui.theme.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LogInScreen : Screen("LogIn_screen")
    object SignUpScreen : Screen("SignUp_screen")
    object SignInScreen : Screen("SignIn_screen")
}
