package com.exirpit.scrumpoker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.exirpit.scrumpoker.ScrumPokerApplication
import com.exirpit.scrumpoker.data.repository.CardRepository
import com.exirpit.scrumpoker.presentation.scrum.home.screen.HomeScreen
import com.exirpit.scrumpoker.presentation.scrum.home.viewModel.HomeScreenViewModel
import com.exirpit.scrumpoker.presentation.common.theme.ScrumPokerTheme
import com.exirpit.scrumpoker.presentation.splash.presentation.SplashScreen
import com.exirpit.scrumpoker.presentation.splash.viewmodel.SplashScreenViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: BaseViewModel = BaseViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onStartup(this)

        setContent {
            ScrumPokerTheme {
                Navigation(viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun Navigation(
    navController: NavHostController = rememberNavController(),
    viewModel: BaseViewModel
) {
    val context = LocalContext.current

    val appIsConfigured = viewModel.applicationLoadedStateFlow.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (!appIsConfigured.value) SplashRoute else HomeRoute
    ) {
        composable(HomeRoute) {
            SplashScreen(
                viewModel = SplashScreenViewModel()
            )
            HomeScreen(
                viewModel = HomeScreenViewModel(
                    CardRepository(
                        BaseViewModel.db.cardDAO,
                        BaseViewModel.db.preferencesDAO
                    )
                )
            )
        }
    }
}