package com.example.projectmanager.ui.theme.view

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectmanager.R
import com.example.projectmanager.ui.theme.component.primaryButton
import com.example.projectmanager.ui.theme.navigation.MainActions

@Composable
fun LoginScreen(actions: MainActions) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.intro_background),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            Text(
                text = "Project Manager",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                fontSize = 40.sp,
                style = typography.h2,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0C90F1),
                textAlign = TextAlign.Center
            )

            Image(
                modifier = Modifier
                    .size(width = 250.dp, height = 250.dp)
                    .padding(top = 30.dp),
                painter = painterResource(id = R.drawable.ic_task_image),
                contentDescription = ""
            )

            Text(
                text = "Let's Get Started",
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color(0xFF363A43),
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Collaborate and Plan Together Across Multiple Devices with Project Manager",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                color = Color(0xFF7A8089),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            primaryButton(
                text = "SIGN IN", modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 40.dp),
                onClick = { }, color = Color(0xFF0A80F5)
            )

            primaryButton(
                text = "SIGN UP", modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp),
                onClick = { actions.gotoSignup() }, color = Color.White,
                textColor = Color(0xFF0A80F5),
                hasStroke = true,
                strokeColor = Color(0xFF0A80F5)
            )
        }
    }
}

