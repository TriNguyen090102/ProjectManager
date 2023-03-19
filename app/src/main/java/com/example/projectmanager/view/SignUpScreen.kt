package com.example.projectmanager.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.projectmanager.Models.User
import com.example.projectmanager.R
import com.example.projectmanager.component.InformationField
import com.example.projectmanager.component.ShowErrorSnackBar
import com.example.projectmanager.component.TopBar
import com.example.projectmanager.component.primaryButton
import com.example.projectmanager.navigation.MainActions
import com.example.projectmanager.repository.AuthRepository
import com.example.projectmanager.utils.Validation.event.ValidationEvent
import com.example.projectmanager.viewmodel.BaseValidationViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignUpScreen(actions: MainActions, viewModel: BaseValidationViewModel) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val state = viewModel.state
    val context = LocalContext.current
    val repository: AuthRepository = AuthRepository()
    viewModel.type = "sign_up"
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(title = "SIGN UP", action = actions) }) {
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
                        InformationField(label = "Name", KeyboardType.Email,
                            onValueChange = { viewModel.onEvent(ValidationEvent.NameChanged(it)) }
                        )
                        InformationField(
                            label = "Email",
                            KeyboardType.Email,
                            onValueChange = { viewModel.onEvent(ValidationEvent.EmailChanged(it)) })
                        InformationField(
                            label = "Password",
                            KeyboardType.Password,
                            visualTransformation = PasswordVisualTransformation(),
                            onValueChange = { viewModel.onEvent(ValidationEvent.PasswordChanged(it)) }
                        )
                        InformationField(
                            label = "Confirm password",
                            KeyboardType.Password,
                            visualTransformation = PasswordVisualTransformation(),
                            onValueChange = {
                                viewModel.onEvent(
                                    ValidationEvent.RepeatedPasswordChanged(it)
                                )
                            }
                        )
                        primaryButton(text = "SIGN UP", modifier = Modifier
                            .padding(top = 10.dp, start = 5.dp, end = 5.dp)
                            .fillMaxWidth(),
                            color = Color(0xFF0A80F5),
                            onClick = {
//                                val db = FirebaseFirestore.getInstance()
//                                val city = hashMapOf(
//                                "name" to "Los Angeles",
//                                "state" to "CA",
//                                "country" to "USA"
//                            )
//
//                                db.collection("cities").document("Indo")
//                                    .set(city)
//                                    .addOnSuccessListener {
//                                        Log.d("created","")
//                                    }
//                                    .addOnFailureListener {
//                                        Log.d("created fail","")
//                                    }
                                viewModel.onEvent(ValidationEvent.Submit)
                                Log.d("error", "${state.nameError}")
                                Log.d("error", "${state.emailError}")
                                Log.d("error", "${state.passwordError}")
                                Log.d("error", "${state.repeatedPasswordError}")
                                if (viewModel.invalid == true) {
                                    showError(viewModel, scaffoldState, coroutineScope)
                                } else {

                                    //create user
                                    registerUser(viewModel = viewModel, context = context,repository)

                                }
                            })


                    }
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")

fun registerUser(viewModel: BaseValidationViewModel, context: Context, repository: AuthRepository) {
    val state = viewModel.state
    viewModel.viewModelScope.launch {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is BaseValidationViewModel.EventResult.Success -> {
                    repository.registerUser(state.name, state.email, state.password)
                }
            }
        }
    }
}


fun showError(
    viewModel: BaseValidationViewModel,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope
) {
    if (viewModel.invalid == true) {
        if (viewModel.state.nameError != null) {
            ShowErrorSnackBar(viewModel.state.nameError!!, scaffoldState, coroutineScope)
        } else if (viewModel.state.emailError != null) {
            ShowErrorSnackBar(viewModel.state.emailError!!, scaffoldState, coroutineScope)

        } else if (viewModel.state.passwordError != null) {
            ShowErrorSnackBar(viewModel.state.passwordError!!, scaffoldState, coroutineScope)
        } else if (viewModel.state.repeatedPasswordError != null) {
            ShowErrorSnackBar(
                viewModel.state.repeatedPasswordError!!,
                scaffoldState,
                coroutineScope
            )
        }
    }

}
