package com.example.projectmanager.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.projectmanager.R
import com.example.projectmanager.component.InformationField
import com.example.projectmanager.component.ShowErrorSnackBar
import com.example.projectmanager.component.TopBar
import com.example.projectmanager.component.primaryButton
import com.example.projectmanager.navigation.MainActions
import com.example.projectmanager.repository.AuthRepository
import com.example.projectmanager.utils.Validation.event.ValidationEvent
import com.example.projectmanager.viewmodel.BaseValidationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignInScreen(actions: MainActions, viewModel: BaseValidationViewModel) {
    viewModel.type = "sign_in"
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val state = viewModel.state
    val context = LocalContext.current
    val repository: AuthRepository = AuthRepository()
    Scaffold(scaffoldState = scaffoldState,topBar = { TopBar(title = "SIGN IN", action = actions) }) {
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

                        InformationField(
                            label = "Email",
                            KeyboardType.Email,
                            onValueChange = { viewModel.onEvent(ValidationEvent.EmailChanged(it)) })
                        InformationField(
                            label = "Password",
                            KeyboardType.Password,
                            visualTransformation = PasswordVisualTransformation(),
                            onValueChange = { viewModel.onEvent(ValidationEvent.PasswordChanged(it)) })

                        primaryButton(text = "SIGN IN", modifier = Modifier
                            .padding(top = 10.dp, start = 5.dp, end = 5.dp)
                            .fillMaxWidth(),
                            color = Color(0xFF0A80F5),
                            onClick = {
                                viewModel.onEvent(ValidationEvent.Submit)


                                if (viewModel.invalid == true) {
                                    Log.d("error", "${state.emailError}")
                                    Log.d("error", "${state.passwordError}")
                                    showSignInError(viewModel, scaffoldState, coroutineScope)
                                } else {
                                    signIn(viewModel = viewModel, context = context,repository)

                                }
                            })
                    }
                }
            }
        }
    }
}

fun signIn(viewModel: BaseValidationViewModel, context: Context, repository: AuthRepository) {
    val state = viewModel.state
    viewModel.viewModelScope.launch {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is BaseValidationViewModel.EventResult.Success -> {
                    repository.signInRegisteredUser( state.email, state.password)
                }
            }
        }
    }
}

fun showSignInError(
    viewModel: BaseValidationViewModel,
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope
) {
    if (viewModel.invalid == true) {
        if (viewModel.state.emailError != null) {
            ShowErrorSnackBar(viewModel.state.emailError!!, scaffoldState, coroutineScope)

        } else if (viewModel.state.passwordError != null) {
            ShowErrorSnackBar(viewModel.state.passwordError!!, scaffoldState, coroutineScope)
        }
    }

}