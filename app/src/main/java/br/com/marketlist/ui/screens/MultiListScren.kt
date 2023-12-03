package br.com.marketlist.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.marketlist.R
import br.com.marketlist.data.ListMarket
import br.com.marketlist.data.ProductItem
import br.com.marketlist.navigation.navigateToFormMarketListScreen
import br.com.marketlist.navigation.navigateToMarketListScreen
import br.com.marketlist.sampledata.sampleFirstList
import br.com.marketlist.sampledata.sampleListItems
import br.com.marketlist.ui.theme.MarketListTheme
import br.com.marketlist.ui.uistate.MultiListUiState
import br.com.marketlist.ui.viewmodels.MultiListViewModel


@Composable
fun MultiListScreen(
    modifier: Modifier = Modifier,
    state: MultiListUiState,
    viewModel: MultiListViewModel,
    navController: NavController
) {

    MultiListScreen(
        modifier = modifier,
        items = state.list,
        onClickList = { idList ->
//            viewModel.onClickListItem(idItem)
            navController.navigateToMarketListScreen(idList)
        },
        onClickNavigateToMarket = {
            navController.navigateToFormMarketListScreen()
        }
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiListScreen(
    modifier: Modifier = Modifier,
    items: Map<ListMarket, List<ProductItem>> = emptyMap(),
    onClickList: (id: Long) -> Unit = {},
    onClickNavigateToMarket: () -> Unit = {},
) {

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
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

        LazyVerticalGrid(
            modifier = modifier
                .padding(paddingValues = paddingValues),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(items.entries.toList()) { (market, products) ->
                Card(
                    modifier = modifier
                        .size(width = 100.dp, height = 200.dp)
                        .clickable {
                            onClickList(market.id)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                ) {
                    Row(
                        modifier = modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = modifier,
                            textAlign = TextAlign.Center,
                            text = market.titleList,
                            fontSize = 15.sp
                        )
                    }
                    products.listIterator().forEach {
                        Row(
                            modifier = modifier
                                .align(alignment = Alignment.Start)
                                .padding(6.dp),
                        ) {
                            Text(text = "${it.name}", fontSize = 10.sp)
                        }
                    }
                }
            }
        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun MultiListScreenPreview() {
    MarketListTheme {
        Surface {
            MultiListScreen(
                items = mapOf(
                    Pair(
                        sampleListItems.first(),
                        sampleFirstList
                    ),
                    Pair(
                        sampleListItems[1],
                        sampleFirstList
                    ),
                    Pair(
                        sampleListItems[2],
                        sampleFirstList
                    )
                )
            )
        }
    }
}