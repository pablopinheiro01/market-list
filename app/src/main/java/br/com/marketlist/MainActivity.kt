package br.com.marketlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.marketlist.model.ProductItem
import br.com.marketlist.sampledata.sampleFirstList
import br.com.marketlist.ui.theme.MarketListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {
    Scaffold() {
        Box(modifier = modifier.padding(it)) {
            HomeListViewMarketScreen(listItens = sampleFirstList)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
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

@Preview
@Composable
fun ProductItemCardComponentPreview() {
    ProductItemCardComponent(item = sampleFirstList.first())
}

@Composable
fun ProductItemCardComponent(
    modifier: Modifier = Modifier,
    item: ProductItem
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(12.dp)
            )
            .heightIn(50.dp)
    ) {
        Row {
            Column {
                Checkbox(
                    checked = false,
                    onCheckedChange = {}
                )
            }
            Spacer(modifier = modifier.width(10.dp))
            Column(
                modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = item.name)
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun AppPreview() {
    MarketListTheme {
        App()
    }
}
