package com.example.projectmanager.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectmanager.Models.User
import com.example.projectmanager.utils.Validation.state.UserState
import com.example.projectmanager.view.*
import com.example.projectmanager.viewmodel.BaseValidationViewModel
import com.example.projectmanager.viewmodel.UserViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavGraph(userViewModel: UserViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val actions = remember(navController) { MainActions(navController) }







    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            val viewModel = hiltViewModel<BaseValidationViewModel>(it)
            //val userViewModel = hiltViewModel<UserViewModel>(LocalContext.current as ViewModelStoreOwner)
            SplashCreen(actions = actions, userViewModel)
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
            val viewModel = hiltViewModel<BaseValidationViewModel>(it)
            userViewModel.getcurrentUser()
            UserScreen(actions = actions, viewModel = viewModel, userViewModel = userViewModel)
        }
        composable(Screen.Profile.route){
            val result = userViewModel.user.value
            var user = User()
            if(result is UserState.alreadyLogin)
            {
                user = result.data
            }
            ProfileScreen(actions = actions,user,userViewModel)
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

    fun gotoUserProfile(){
        navController.navigate(Screen.Profile.route)
    }


}