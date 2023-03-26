package com.example.projectmanager.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.projectmanager.R
import com.example.projectmanager.component.InformationField
import com.example.projectmanager.component.TopBar
import com.example.projectmanager.component.primaryButton
import com.example.projectmanager.navigation.MainActions
import com.example.projectmanager.repository.AuthRepository
import com.example.projectmanager.utils.Validation.event.ValidationEvent
import com.example.projectmanager.viewmodel.UserViewModel
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    actions: MainActions,
    user: com.example.projectmanager.Models.User,
    userViewModel: UserViewModel
) {

    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val repository: AuthRepository = AuthRepository()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(title = "My Profile", onClick = { actions.upPress() }) }) {
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
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.White, CircleShape)
                                .background(Color.Gray),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                                    .padding(10.dp),
                                painter = rememberImagePainter(data = user.image),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                        var name: String = ""
                        var email: String = ""
                        var mobile: String = ""

                        InformationField(
                            label = "Name",
                            KeyboardType.Email,
                            onValueChange = {
                                name = it
                            },
                            rememberTextValue = user.name
                        )
                        InformationField(
                            label = "Email",
                            KeyboardType.Email,
                            onValueChange = {
                                email = it
                            },
                            rememberTextValue = user.email
                        )
                        InformationField(
                            label = "Mobile",
                            KeyboardType.Phone,
                            onValueChange = {
                                mobile = it
                            },
                            rememberTextValue = if (user.mobile.toString() == "0") "" else user.mobile.toString()
                        )

                        primaryButton(text = "UPDATE", modifier = Modifier
                            .padding(top = 10.dp, start = 5.dp, end = 5.dp)
                            .fillMaxWidth(),
                            color = Color(0xFF0A80F5),
                            onClick = {
                                userViewModel.updateUser(name, email, mobile)
                            })
                    }
                }
            }
        }
    }
}

