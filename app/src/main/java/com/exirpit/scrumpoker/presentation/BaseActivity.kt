package com.exirpit.scrumpoker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.exirpit.scrumpoker.presentation.singlePlayer.screen.SinglePlayerScreen
import com.exirpit.scrumpoker.presentation.singlePlayer.viewModel.SinglePlayerViewModel
import com.exirpit.scrumpoker.presentation.startMenu.screen.StartMenuScreen
import com.exirpit.scrumpoker.presentation.startMenu.viewModel.StartMenuViewModel
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrumPokerTheme {
                Navigation()
            }
        }
    }
}

@Composable
private fun Navigation(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = StartMenuRoute
    ) {
        composable(StartMenuRoute) {
            StartMenuScreen(
                viewModel = StartMenuViewModel(context),
                onButtonNavigationClick = {
                    navController.navigate(it)
                }
            )
        }
        composable(SinglePlayerRoute) {
            SinglePlayerScreen(
                viewModel = SinglePlayerViewModel()
            )
        }
    }
}