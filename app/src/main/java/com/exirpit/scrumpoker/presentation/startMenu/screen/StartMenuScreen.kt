package com.exirpit.scrumpoker.presentation.startMenu.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exirpit.scrumpoker.presentation.MultiPlayerRoute
import com.exirpit.scrumpoker.presentation.SettingsRoute
import com.exirpit.scrumpoker.presentation.SinglePlayerRoute
import com.exirpit.scrumpoker.presentation.startMenu.viewModel.StartMenuViewModel
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTheme
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTypography

@Composable
fun StartMenuScreen(
    viewModel: StartMenuViewModel = viewModel(),
    onButtonNavigationClick: (String) -> Unit
) {
    val versionNameState = viewModel.versionName.collectAsState()
    val versionCodeState = viewModel.versionCode.collectAsState()
    ScrumPokerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val buttonModifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
            Spacer(modifier = Modifier.weight(1f))
            StartMenuButton(
                onClick = onButtonNavigationClick,
                text = "Single player",
                navigationPath = SinglePlayerRoute,
                modifier = buttonModifier
            )
            Spacer(modifier = Modifier.height(8.dp))
            StartMenuButton(
                onClick = onButtonNavigationClick,
                text = "Multiplayer",
                navigationPath = MultiPlayerRoute,
                modifier = buttonModifier
            )
            Spacer(modifier = Modifier.height(8.dp))
            StartMenuButton(
                onClick = onButtonNavigationClick,
                text = "Settings",
                navigationPath = SettingsRoute,
                modifier = buttonModifier
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "ScrumPoker: version ${versionNameState.value}.${versionCodeState.value}",
                color = MaterialTheme.colorScheme.primary,
                style = ScrumPokerTypography.bodySmall
            )
        }
    }
}

@Composable
private fun StartMenuButton(
    onClick: (String) -> Unit,
    text: String,
    navigationPath: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            onClick.invoke(navigationPath)
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = text,
            style = ScrumPokerTypography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StartMenuScreenPreview() {
    ScrumPokerTheme() {
        val context = LocalContext.current
        StartMenuScreen(
            viewModel = StartMenuViewModel(context),
            onButtonNavigationClick = { }
        )
    }
}