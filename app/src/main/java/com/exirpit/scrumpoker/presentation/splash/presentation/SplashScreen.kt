package com.exirpit.scrumpoker.presentation.splash.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.exirpit.scrumpoker.R
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTheme
import com.exirpit.scrumpoker.presentation.splash.viewmodel.SplashScreenViewModel

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = viewModel()
) {
    ScrumPokerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher),
                    contentDescription = "App icon",
                    modifier = Modifier
                        .padding(35.dp)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    ScrumPokerTheme {
        SplashScreen(
            SplashScreenViewModel()
        )
    }
}