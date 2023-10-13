package br.com.marketlist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.marketlist.model.ProductItem
import br.com.marketlist.ui.components.ProductItemCardComponent

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