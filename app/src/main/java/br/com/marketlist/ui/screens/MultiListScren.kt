package br.com.marketlist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.marketlist.data.ProductItem
import br.com.marketlist.sampledata.sampleFirstList
import br.com.marketlist.ui.theme.MarketListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiListScreen(
    modifier: Modifier = Modifier,
    items: List<ProductItem> = emptyList()
) {

    Scaffold(
        modifier = modifier
            .fillMaxSize()
    ) { paddingValues ->

        LazyVerticalGrid(
            modifier = modifier.padding(paddingValues = paddingValues),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(items) { item ->
                Card(
                    modifier = modifier.size(width = 100.dp, height = 100.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                ) {
                    Row {
                        Text(
                            modifier = modifier,
                            textAlign = TextAlign.Center,
                            text = "Titulo da Lista",
                            fontSize = 15.sp
                        )
                    }
                    Row {
                        Text(text = "${item.name}", fontSize = 10.sp)
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
            MultiListScreen(items = sampleFirstList)
        }
    }
}