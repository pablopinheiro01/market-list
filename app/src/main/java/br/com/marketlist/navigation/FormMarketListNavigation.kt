package br.com.marketlist.navigation

import android.util.Log
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
const val idListArgument = "idList"

fun NavGraphBuilder.formMarketListScreen(
    navController: NavHostController
) {
    composable(route = "${formMarketListRoute}/{$idListArgument}") {

        val viewModel = hiltViewModel<FormMarketListViewModel>()
        val state by viewModel.uiState.collectAsState()
        val context = LocalContext.current

        val idList = it.arguments?.getString(idListArgument)

        Log.i("formMarketListScreen", "$idList")

        FormMarketListScreen(
            state = state,
            viewModel = viewModel,
            navController = navController
        )
    }
}

fun NavController.navigateToFormMarketListScreen(idList: Long = -1) {
    navigate("${formMarketListRoute}/$idList")
}