package com.exirpit.scrumpoker.presentation.common.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SPTopAppBarDefault(
    modifier: Modifier = Modifier,
    title: String,
    onNavigationClick: () -> Unit,
    onActionClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigationClick
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Navigation drawer" //TODO localize
                )
            }
        },
        actions = {
            IconButton(
                onClick = onActionClick
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "User profile" //TODO localize
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SPTopAppBarNavigated(
    modifier: Modifier = Modifier,
    title: String,
    onNavigationClick: () -> Unit,
    onActionClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigationClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Go back" //TODO localize
                )
            }
        },
        actions = {
            IconButton(
                onClick = onActionClick
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "User profile" //TODO localize
                )
            }
        }
    )
}