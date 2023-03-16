package com.example.projectmanager.ui.theme.view

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectmanager.R
import com.example.projectmanager.ui.theme.component.InformationField
import com.example.projectmanager.ui.theme.component.TopBar
import com.example.projectmanager.ui.theme.component.primaryButton
import com.example.projectmanager.ui.theme.navigation.MainActions

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignUpScreen(actions: MainActions) {

    Scaffold(topBar = { TopBar(title = "SIGN UP", action = actions) }) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_background),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Enter your name, email and password to register with us.",
                    modifier = Modifier.padding(start = 25.dp, end = 25.dp),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF363A43)
                )
                Spacer(modifier = Modifier.height(25.dp))
                Card(
                    modifier = Modifier
                        .padding(16.dp),
                    elevation = 10.dp
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    Column(
                        modifier = Modifier.padding(
                            top = 25.dp,
                            bottom = 25.dp,
                            start = 15.dp,
                            end = 15.dp
                        )
                    ) {
                        InformationField(label = "Name", KeyboardType.Email)
                        InformationField(label = "Email", KeyboardType.Email)
                        InformationField(
                            label = "Password",
                            KeyboardType.Password,
                            visualTransformation = PasswordVisualTransformation()
                        )
                        primaryButton(text = "SIGN UP", modifier = Modifier
                            .padding(top = 10.dp, start = 5.dp, end = 5.dp)
                            .fillMaxWidth(),
                            color = Color(0xFF0A80F5),
                            onClick = { })
                    }
                }
            }
        }
    }
}
