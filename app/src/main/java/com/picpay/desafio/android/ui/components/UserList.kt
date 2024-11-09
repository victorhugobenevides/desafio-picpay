package com.picpay.desafio.android.ui.components

import UserListItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.ui.theme.colorPrimary
import com.picpay.desafio.android.ui.theme.typography

@Composable
fun UserList(users: List<User>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorPrimary)
            .padding(16.dp)
            .testTag("UserList")
    ) {
        item {
            Text(
                text = stringResource(id = R.string.title),
                style = typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.padding(
                    bottom = 32.dp,
                    top = 24.dp
                )
                    .testTag(stringResource(id = R.string.title)),
                fontWeight = FontWeight.Bold
            )
        }
        
        items(users,  key = { it.id }) { user ->
            UserListItem(user)
        }
    }
}
