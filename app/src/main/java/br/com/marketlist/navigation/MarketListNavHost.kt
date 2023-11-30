package br.com.marketlist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MarkListNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = multiListScreenRoute
    ){
        marketListScreen(navController)
        formMarketListScreen(navController)
        multiListScreen(navController)
    }
}
