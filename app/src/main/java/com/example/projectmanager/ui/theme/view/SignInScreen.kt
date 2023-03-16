package com.example.projectmanager.ui.theme.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectmanager.R
import com.example.projectmanager.ui.theme.component.InformationField
import com.example.projectmanager.ui.theme.component.TopBar
import com.example.projectmanager.ui.theme.component.primaryButton
import com.example.projectmanager.ui.theme.navigation.MainActions

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignInScreen(actions: MainActions) {

    Scaffold(topBar = { TopBar(title = "SIGN IN", action = actions) }) {
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
                    text = "Enter your email and password to sign in.",
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

                        InformationField(label = "Email", KeyboardType.Email)
                        InformationField(
                            label = "Password",
                            KeyboardType.Password,
                            visualTransformation = PasswordVisualTransformation()
                        )
                        primaryButton(text = "SIGN IN", modifier = Modifier
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