package com.rogoz208.qafpay.presentation.screen_transactions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import com.rogoz208.qafpay.presentation.Screen
import com.rogoz208.qafpay.presentation.screen_transactions.composnents.TransactionListItem

@Composable
fun TransactionsScreen(
    navBackStackEntry: NavBackStackEntry,
    viewModel: TransactionsViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val args = navBackStackEntry.toRoute<Screen.TransactionsScreen>()

    LaunchedEffect(key1 = args) {
        viewModel.onGettingAccountId(args.accountId)
    }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.transactions) { transition ->
                    TransactionListItem(transaction = transition) {

                    }
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}