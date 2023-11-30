package br.com.marketlist.navigation

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.marketlist.sampledata.sampleFirstList
import br.com.marketlist.ui.screens.HomeListViewMarketScreen
import br.com.marketlist.ui.viewmodels.MarketListViewModel

const val marketListRoute = "marketList"
fun NavGraphBuilder.marketListScreen(
    navController: NavController
) {
    composable("${marketListRoute}/{$idListArgument}") {

        val viewModel = hiltViewModel<MarketListViewModel>()
        val state by viewModel.uiState.collectAsState()
        val context = LocalContext.current

        val idList = it.arguments?.getString(idListArgument)

        Log.i("marketListScreen", "$idList")

        HomeListViewMarketScreen(
            state = state,
            viewModel = viewModel ,
            navController = navController
        )
    }

}

fun NavController.navigateToMarketListScreen(
    idLista:Long,
    navOptions: NavOptions? = null
) {
    navigate("${marketListRoute}/$idLista", navOptions)
}