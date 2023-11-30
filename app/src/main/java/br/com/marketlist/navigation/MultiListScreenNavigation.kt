package br.com.marketlist.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.marketlist.ui.screens.MultiListScreen
import br.com.marketlist.ui.viewmodels.MultiListViewModel

const val multiListScreenRoute = "multiList"

fun NavGraphBuilder.multiListScreen(
    navController: NavController
){
    composable(multiListScreenRoute){

        val viewModel = hiltViewModel<MultiListViewModel>()
        val state by viewModel.uiState.collectAsState()

        MultiListScreen(
            state = state ,
            viewModel = viewModel,
            navController = navController
        )

    }
}

fun NavController.navigateToMultiListScreen(
    navOptions: NavOptions? = null
){
    navigate(multiListScreenRoute, navOptions)
}