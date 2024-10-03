package com.exirpit.scrumpoker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.exirpit.scrumpoker.presentation.screens.home.HomeScreen
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTheme
import com.exirpit.scrumpoker.presentation.screens.home.drawer.about.AboutScreen
import com.exirpit.scrumpoker.presentation.screens.home.drawer.cards.CardsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable(HomeRoute) {
            HomeScreen(onNavigationItemClicked = { it ->
                when (it) { //TODO use localized strings
                    "User" -> {
                        TODO()
                    }

                    "Cards" -> {
                        navController.navigate(CardsScreenRoute)
                    }

                    "Settings" -> {
                        TODO()
                    }

                    "About" -> {
                        navController.navigate(AboutScreenRoute)
                    }
                }
            }
            )
        }
        composable(AboutScreenRoute) {
            AboutScreen {
                navController.popBackStack()
            }
        }
        composable(CardsScreenRoute) {
            CardsScreen(onNavigateBackClicked = {
                navController.popBackStack() }
            ) {
                navController.navigate(CardsEditScreenRoute)
            }
        }
        composable(CardsEditScreenRoute) {
            TODO()
        }
    }
}