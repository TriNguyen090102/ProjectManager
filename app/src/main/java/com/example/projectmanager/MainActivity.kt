package com.example.projectmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.projectmanager.navigation.NavGraph

import com.example.projectmanager.viewmodel.UserViewModel
import com.google.firebase.FirebaseApp
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val userViewModel: UserViewModel = hiltViewModel()
            NavGraph(userViewModel)
        }
    }
}

