package com.example.projectmanager.component

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch




fun ShowErrorSnackBar(msg: String,scaffoldState: ScaffoldState,coroutineScope: CoroutineScope) {

    coroutineScope.launch {
        scaffoldState.snackbarHostState.showSnackbar(
            message = msg,
            actionLabel = "Get it",
            duration = SnackbarDuration.Long
        )
        Log.d("msg","error showed")
    }
}