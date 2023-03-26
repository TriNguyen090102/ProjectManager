package com.example.projectmanager.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.projectmanager.component.NavigationDraw
import com.example.projectmanager.component.TopBar
import com.example.projectmanager.navigation.MainActions
import com.example.projectmanager.utils.Validation.state.UserState
import com.example.projectmanager.viewmodel.BaseValidationViewModel
import com.example.projectmanager.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun UserScreen(
    actions: MainActions,
    viewModel: BaseValidationViewModel,
    userViewModel: UserViewModel
) {

    val result = userViewModel.user.value
    Log.d("userscreen result","$result")
    when(result){
        is UserState.alreadyLogin ->{
            val user = result.data
            Log.d("user profile:","${user.name}")
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val context = LocalContext.current as ComponentActivity
            Scaffold(
                scaffoldState = scaffoldState,
                modifier = Modifier.fillMaxSize(),
                drawerContent = {
                    NavigationDraw(user,scaffoldState,userViewModel, actions,context )
                },
                topBar = {
                    TopBar(
                        title = "Project Manager",
                        onClick = { scope.launch { scaffoldState.drawerState.open() } },
                        icon = Icons.Default.Menu
                    )
                },
                drawerGesturesEnabled = scaffoldState.drawerState.isOpen

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                ) {
                    Text(text = "hello user")
                }
            }
        }
        else -> {
            Text(text = "Somthing Error")
        }
    }
}