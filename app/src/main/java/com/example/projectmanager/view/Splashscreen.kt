package com.example.projectmanager.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectmanager.R
import com.example.projectmanager.navigation.MainActions
import com.example.projectmanager.repository.AuthRepository
import com.example.projectmanager.ui.theme.typography
import kotlinx.coroutines.delay


@Composable
fun SplashCreen(actions: MainActions) {
    LaunchedEffect(key1 = true){
        delay(3000)
        val currentUser = AuthRepository().getCurrentUserId()
        if(!currentUser.isNullOrEmpty()){
            actions.gotoUser()
        }else{
            actions.gotoLogin()
        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_splash_background),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                modifier = Modifier
                    .size(width = 200.dp, height = 200.dp),
                painter = painterResource(id = R.drawable.pm_logo_white),
                contentDescription = ""
            )

            Text(
                text = "Project Manager",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                fontSize = 30.sp,
                style = typography.h2,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }


    }

}