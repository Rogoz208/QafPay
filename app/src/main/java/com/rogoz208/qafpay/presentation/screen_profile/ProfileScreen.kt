package com.rogoz208.qafpay.presentation.screen_profile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rogoz208.qafpay.presentation.screen_profile.components.DropDownTextField

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Profile",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                value = state.profile?.name ?: "",
                onValueChange = {
                    viewModel.onNameUpdate(it)
                },
                singleLine = true,
                prefix = { Text(text = "Name:") }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                value = state.profile?.email ?: "",
                onValueChange = {
                    viewModel.onEmailUpdate(it)
                },
                singleLine = true,
                prefix = { Text(text = "Email:") }
            )
            DropDownTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                items = state.countries.map { it.name },
                onSelectedItemChange = {
                    viewModel.onCountryUpdate(it)
                },
                selectedItem = state.profile?.country ?: "",
                prefix = "Country:"
            )
            DropDownTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                items = state.cities.map { it.name },
                onSelectedItemChange = {
                    viewModel.onCityUpdate(it)
                },
                selectedItem = state.profile?.city ?: "",
                prefix = "City:"
            )
            DropDownTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                items = state.languages.map { it.code },
                onSelectedItemChange = {
                    viewModel.onLanguageUpdate(it)
                },
                selectedItem = state.profile?.language ?: "",
                prefix = "Language:"
            )

            if (state.isChanged) {
                val context = LocalContext.current
                Button(onClick = {
                    Toast.makeText(
                        context,
                        "Не работает. Причину написал в телеграме.",
                        Toast.LENGTH_LONG
                    ).show()
                }) {
                    Text(text = "Save changes")
                }
            }
        }
    }
}
