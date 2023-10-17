package br.com.marketlist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.marketlist.data.ProductItem
import br.com.marketlist.sampledata.sampleFirstList
import br.com.marketlist.ui.components.ProductItemCardComponent
import br.com.marketlist.ui.theme.MarketListTheme

@Composable
fun HomeListViewMarketScreen(
    modifier: Modifier = Modifier,
    listItens: List<ProductItem>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        item { Spacer(modifier = Modifier) }
        items(listItens) { item ->
            ProductItemCardComponent(item = item)
        }
        item { Spacer(modifier = Modifier) }
    }

}

@Preview(showSystemUi = true)
@Composable
fun HomeListViewMarketScreenPreview() {
    MarketListTheme {
        Surface {
            HomeListViewMarketScreen(listItens = sampleFirstList)
        }
    }
}