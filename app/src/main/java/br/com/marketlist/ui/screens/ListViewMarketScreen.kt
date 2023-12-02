package br.com.marketlist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.marketlist.data.ProductItem
import br.com.marketlist.sampledata.sampleFirstList
import br.com.marketlist.ui.components.ProductItemCardComponent
import br.com.marketlist.ui.theme.MarketListTheme
import br.com.marketlist.ui.uistate.MarketListUiState
import br.com.marketlist.ui.viewmodels.MarketListViewModel

@Composable
fun ListViewMarketScreen(
    state: MarketListUiState,
    viewModel: MarketListViewModel,
    navController: NavController
) {

    ListViewMarketScreen(
        modifier = Modifier,
        items = state.products,
        isShowFinishedMarket = state.isShowFinishedMarket,
        onClickNavigateToMarket = {
//            navController.navigateToFormMarketListScreen()
        },
        onClickItemCheck = { id ->
            viewModel.onClickBoughtItem(id)
        },
        onClickDeleteAllList = {
            viewModel.onClickDeleteAllList()
            navController.popBackStack()
        },
        onBack = {
            navController.popBackStack()
        },
        title = state.titleList
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListViewMarketScreen(
    modifier: Modifier = Modifier,
    items: List<ProductItem> = emptyList(),
    title: String = "",
    isShowFinishedMarket: Boolean = false,
    onClickNavigateToMarket: () -> Unit = {},
    onClickItemCheck: (idItemClicked: Long) -> Unit = {},
    onClickDeleteAllList: () -> Unit = {},
    onBack: () -> Unit = {}
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),

                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Desc"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onClickDeleteAllList() }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "desc"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(6.dp)
        ) {
            if (!isShowFinishedMarket) {
                item { Spacer(modifier = modifier) }
                items(items) { item ->
                    ProductItemCardComponent(
                        item = item,
                        onClickItem = { idItemClicked ->
                            onClickItemCheck(idItemClicked)
                        }
                    )
                }
                item { Spacer(modifier = modifier) }
            } else {
                item { Text(text = "Finished Congrats!") }
            }
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun HomeListViewMarketScreenPreview() {
    MarketListTheme {
        Surface {
            ListViewMarketScreen(items = sampleFirstList, title = "Lista de Eletronicos")
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun HomeListViewMarketScreenPreviewEmtpyList() {
    MarketListTheme {
        Surface {
            ListViewMarketScreen(isShowFinishedMarket = true, items = emptyList())
        }
    }
}