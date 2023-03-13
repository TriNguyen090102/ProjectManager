package com.example.projectmanager.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectmanager.ui.theme.view.LoginScreen
import com.example.projectmanager.ui.theme.view.SplashCreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val actions = remember(navController) { MainActions(navController) }

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashCreen(actions = actions)
        }
        composable(Screen.LogInScreen.route) {
            LoginScreen()
        }
    }

}

class MainActions(private val navController: NavController) {
    fun gotoLogin() {
        navController.navigate(Screen.LogInScreen.route)
    }
}