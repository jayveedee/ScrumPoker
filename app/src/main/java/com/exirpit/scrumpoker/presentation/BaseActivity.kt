package com.exirpit.scrumpoker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.exirpit.scrumpoker.presentation.singlePlayer.screen.SinglePlayerScreen
import com.exirpit.scrumpoker.presentation.singlePlayer.viewModel.SinglePlayerViewModel
import com.exirpit.scrumpoker.presentation.startMenu.screen.StartMenuScreen
import com.exirpit.scrumpoker.presentation.startMenu.viewModel.StartMenuViewModel
import com.exirpit.scrumpoker.presentation.theme.ScrumPokerTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    navController: NavHostController = rememberAnimatedNavController()
) {
    val context = LocalContext.current

    AnimatedNavHost(
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