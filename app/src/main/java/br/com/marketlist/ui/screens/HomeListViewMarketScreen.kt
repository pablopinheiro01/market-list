package br.com.marketlist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import br.com.marketlist.R
import br.com.marketlist.data.ProductItem
import br.com.marketlist.sampledata.sampleFirstList
import br.com.marketlist.ui.components.ProductItemCardComponent
import br.com.marketlist.ui.theme.MarketListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeListViewMarketScreen(
    modifier: Modifier = Modifier,
    listItens: List<ProductItem>
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),

                title = { Text(text = stringResource(id = R.string.app_name)) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
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
            contentPadding = PaddingValues(8.dp)
        ) {
            item { Spacer(modifier = Modifier) }
            items(listItens) { item ->
                ProductItemCardComponent(item = item)
            }
            item { Spacer(modifier = Modifier) }
        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormMarketListScreen(
    modifier: Modifier = Modifier
) {
    val teste = ""
    Scaffold { paddingValues ->
        Column(modifier.padding(paddingValues)) {
            Column(
                modifier.padding(start = 16.dp, top = 16.dp, bottom = 0.dp, end= 16.dp)

            ) {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 300.dp, max = 330.dp),
                    value = teste,
                    onValueChange = { },
                )
            }
            Column(
                modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
            ) {
                Button(onClick = { /*TODO*/ }, modifier = modifier.fillMaxWidth())
                {
                    Text("Transform")
                }
            }
        }

    }
}

@Preview
@Composable
fun FormMarketListScreenPreview() {
    MarketListTheme {
        Surface {
            FormMarketListScreen()
        }
    }
    
}
