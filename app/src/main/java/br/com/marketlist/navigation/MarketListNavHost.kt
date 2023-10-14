package br.com.marketlist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.marketlist.sampledata.sampleFirstList
import br.com.marketlist.ui.screens.HomeListViewMarketScreen

@Composable
fun MarkListNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = marketListRoute
    ){
        marketListScreen()
    }
}
