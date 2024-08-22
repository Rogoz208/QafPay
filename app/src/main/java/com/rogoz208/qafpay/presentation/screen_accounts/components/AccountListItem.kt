package com.rogoz208.qafpay.presentation.screen_accounts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rogoz208.qafpay.domain.model.Account

@Composable
fun AccountListItem(
    account: Account,
    onItemClick: (Account) -> Unit
) {

    Spacer(modifier = Modifier.size(8.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .clickable { onItemClick(account) }
        .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = account.name,
            textAlign = TextAlign.Start
        )
        Text(
            text = "${account.balance} ${account.currency.symbol}",
            textAlign = TextAlign.End
        )
    }
    Spacer(modifier = Modifier.size(8.dp))
    HorizontalDivider()
}