package com.rogoz208.qafpay.presentation.screen_auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rogoz208.qafpay.domain.model.Data
import com.rogoz208.qafpay.presentation.Screen

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val progressBarAlpha = if (state.isLoading) 100f else 0f

    val context = LocalContext.current
    LaunchedEffect(state.isOtpVerified, state.isOtpSent) {
        if (state.isOtpVerified) {
            navController.navigate(Screen.MainBottomNavigationScreen)
        }
        if (state.isOtpSent) {
            when (val data = state.auth?.data) {
                is Data.OtpSendData -> {
                    Toast.makeText(
                        context,
                        "Password: ${data.otp}",
                        Toast.LENGTH_LONG
                    ).show()
                }

                else -> {}
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {

            if (!state.isOtpSent) {
                OutlinedTextField(
                    value = state.email,
                    onValueChange = { viewModel.updateEmail(it) },
                    label = { Text(text = "Enter your email") },
                    trailingIcon = {
                        Icon(imageVector = Icons.Outlined.Email, contentDescription = "Email")
                    },
                    supportingText = {
                        if (state.errorMessage.isNotBlank()) {
                            Text(text = state.errorMessage)
                        }
                    },
                    isError = state.errorMessage.isNotBlank(),
                    enabled = !state.isLoading,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        viewModel.otpSend(state.email)
                    })
                )
            } else {
                OutlinedTextField(
                    value = state.enteredOtp,
                    onValueChange = { viewModel.updateOtp(it) },
                    label = { Text(text = "Enter one time password") },
                    trailingIcon = {
                        Icon(imageVector = Icons.Outlined.Lock, contentDescription = "Password")
                    },
                    supportingText = {
                        if (state.errorMessage.isNotBlank()) {
                            Text(text = state.errorMessage)
                        }
                    },
                    isError = state.errorMessage.isNotBlank(),
                    enabled = !state.isLoading,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        viewModel.otpVerify(state.enteredOtp)
                    })
                )
            }

            Spacer(modifier = Modifier.size(32.dp))
            CircularProgressIndicator(modifier = Modifier.alpha(progressBarAlpha))
        }
    }
}
