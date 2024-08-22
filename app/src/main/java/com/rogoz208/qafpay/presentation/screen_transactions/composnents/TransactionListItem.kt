package com.rogoz208.qafpay.presentation.screen_transactions.composnents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rogoz208.qafpay.domain.model.Transaction

@Composable
fun TransactionListItem(
    transaction: Transaction,
    onItemClick: (Transaction) -> Unit
) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(transaction) }
        .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = transaction.description,
                textAlign = TextAlign.Start
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${transaction.amount} ${transaction.currency?.symbol}",
                    textAlign = TextAlign.End
                )
                Text(
                    text = transaction.status,
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            HorizontalDivider()
        }
    }
}