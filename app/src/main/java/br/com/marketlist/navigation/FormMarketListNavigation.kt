package br.com.marketlist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.marketlist.ui.screens.FormMarketListScreen
import br.com.marketlist.ui.uistate.FormMarketUiState

const val formMarketListRoute = "formMarketList"

fun NavGraphBuilder.formMarketListScreen(navController: NavHostController) {
    composable(formMarketListRoute){
        FormMarketListScreen(state = FormMarketUiState())
    }
}

fun NavController.navigateToFormMarketListScreen(

){
    navigate(formMarketListRoute)
}