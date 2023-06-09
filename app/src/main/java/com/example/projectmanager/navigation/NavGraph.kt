package com.example.projectmanager.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectmanager.view.*
import com.example.projectmanager.viewmodel.BaseValidationViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val actions = remember(navController) { MainActions(navController) }

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            val viewModel = hiltViewModel<BaseValidationViewModel>(it)
            SplashCreen(actions = actions)
        }
        composable(Screen.LogInScreen.route) {
            LoginScreen(actions = actions)
        }
        composable(Screen.SignUpScreen.route){
            val viewModel = hiltViewModel<BaseValidationViewModel>(it)
            SignUpScreen(actions = actions,viewModel)
        }
        composable(Screen.SignInScreen.route){
            val viewModel = hiltViewModel<BaseValidationViewModel>(it)
            SignInScreen(actions = actions,viewModel)
        }

        composable(Screen.UserScreen.route){
            UserScreen()
        }
    }

}

class MainActions(private val navController: NavController) {
    fun gotoLogin() {
        navController.navigate(Screen.LogInScreen.route)
    }
    fun upPress(){
        navController.navigateUp()
    }
    fun gotoSignup(){
        navController.navigate(Screen.SignUpScreen.route)
    }

    fun gotoSignin(){
        navController.navigate(Screen.SignInScreen.route)
    }

    fun gotoUser(){
        navController.navigate(Screen.UserScreen.route)
    }
}