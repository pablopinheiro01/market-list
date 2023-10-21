package br.com.marketlist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.marketlist.sampledata.sampleFirstList
import br.com.marketlist.ui.screens.HomeListViewMarketScreen

const val marketListRoute = "marketList"
fun NavGraphBuilder.marketListScreen(
    navController: NavController
) {
    composable(marketListRoute) {
        HomeListViewMarketScreen(
            listItens = sampleFirstList,
            onClickNavigateToMarket = { navController.navigateToFormMarketListScreen() }
        )
    }
}

fun NavController.navigateToMarketListScreen(
    navOptions: NavOptions? = null
) {
    navigate(marketListRoute, navOptions)
}