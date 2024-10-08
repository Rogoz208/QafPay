package com.rogoz208.qafpay.presentation.screen_profile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rogoz208.qafpay.presentation.screen_profile.components.DropDownTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "Profile") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
