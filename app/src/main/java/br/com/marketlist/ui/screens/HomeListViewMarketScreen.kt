package br.com.marketlist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.marketlist.R
import br.com.marketlist.data.ItemsProduct
import br.com.marketlist.data.ProductItem
import br.com.marketlist.navigation.navigateToFormMarketListScreen
import br.com.marketlist.sampledata.sampleFirstItemsProduct
import br.com.marketlist.sampledata.sampleFirstList
import br.com.marketlist.ui.components.ProductItemCardComponent
import br.com.marketlist.ui.theme.MarketListTheme
import br.com.marketlist.ui.uistate.FormMarketUiState
import br.com.marketlist.ui.uistate.MarketListUiState
import br.com.marketlist.ui.viewmodels.FormMarketListViewModel
import br.com.marketlist.ui.viewmodels.MarketListViewModel

@Composable
fun HomeListViewMarketScreen(
    state: MarketListUiState,
    viewModel: MarketListViewModel,
    navController: NavController
) {

    HomeListViewMarketScreen(
        modifier = Modifier,
        items = state.products,
        isShowFinishedMarket = state.isShowFinishedMarket,
        onClickNavigateToMarket = {
            navController.navigateToFormMarketListScreen()
        },
        onClickItemCheck = { id ->
            viewModel.onClickBoughtItem(id)
        },
        onClickDeleteAllList = {
            viewModel.onClickDeleteAllList()
        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeListViewMarketScreen(
    modifier: Modifier = Modifier,
    items: List<ProductItem> = emptyList(),
    isShowFinishedMarket: Boolean = false,
    onClickNavigateToMarket: () -> Unit = {},
    onClickItemCheck: (idItemClicked: Long) -> Unit = {},
    onClickDeleteAllList: () -> Unit = {}
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),

                title = { Text(text = stringResource(id = R.string.title_list)) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onClickNavigateToMarket()
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_new)
                )
            }
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
                    if (!item.bought) {
                        ProductItemCardComponent(
                            item = item,
                            onClickItem = { idItemClicked ->
                                onClickItemCheck(idItemClicked)
                            }
                        )
                    }
                }
                item { Spacer(modifier = modifier) }
            }else{
                item{Text(text = "Finished Congrats!")}
            }
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun HomeListViewMarketScreenPreview() {
    MarketListTheme {
        Surface {
            HomeListViewMarketScreen(items = sampleFirstList)
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun HomeListViewMarketScreenPreviewEmtpyList() {
    MarketListTheme {
        Surface {
            HomeListViewMarketScreen(isShowFinishedMarket = true, items = emptyList())
        }
    }
}