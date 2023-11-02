package br.com.marketlist.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.marketlist.ui.screens.FormMarketListScreen
import br.com.marketlist.ui.viewmodels.FormMarketListViewModel

const val formMarketListRoute = "formMarketList"

fun NavGraphBuilder.formMarketListScreen(
    navController: NavHostController
) {
    composable(formMarketListRoute) {

        val viewModel = hiltViewModel<FormMarketListViewModel>()
        val state by viewModel.uiState.collectAsState()
        val context = LocalContext.current

        FormMarketListScreen(
            state = state,
            viewModel = viewModel
        )
    }
}

fun NavController.navigateToFormMarketListScreen(

) {
    navigate(formMarketListRoute)
}