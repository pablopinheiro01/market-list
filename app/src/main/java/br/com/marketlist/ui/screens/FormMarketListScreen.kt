package br.com.marketlist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.marketlist.ui.components.RowDataFormatedComponent
import br.com.marketlist.ui.theme.MarketListTheme
import br.com.marketlist.ui.uistate.FormMarketUiState
import br.com.marketlist.ui.viewmodels.FormMarketListViewModel


@Composable
fun FormMarketListScreen(
    state: FormMarketUiState,
    viewModel: FormMarketListViewModel,
    navController: NavHostController
) {
    FormMarketListScreen(
        state = state,
        onValueChangeList = {
            state.onValueChangeList(it)
        },
        onClickTransformToList = {
            viewModel.transformStringToItems()
        },
        onSaveMarketList = {
            state.onSaveMarketList()
            navController.popBackStack()
        },
        onRemoveItemTransformed = {
            viewModel.removeOptionListTransformed(it)
        },
        onValueChangeTitleList = {
            state.onValueChangeTitleList(it)
        },
        onClickBack = {
            navController.navigateUp()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FormMarketListScreen(
    modifier: Modifier = Modifier,
    state: FormMarketUiState,
    onValueChangeList: (value: String) -> Unit = {},
    onValueChangeTitleList: (value:String) -> Unit = {},
    onClickTransformToList: () -> Unit = {},
    onSaveMarketList: () -> Unit = {},
    onRemoveItemTransformed: (position: Int) -> Unit = {},
    onClickBack: () -> Unit = {}
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val modifierTextField = if (state.showSaveBottom) {
        modifier
            .padding(start = 16.dp, top = 16.dp, bottom = 0.dp, end = 16.dp)
            .height(190.dp)
    } else {
        modifier
            .padding(start = 16.dp, top = 16.dp, bottom = 0.dp, end = 16.dp)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),

                title = { Text(text = "Cadastrar Lista") },
                navigationIcon = {
                    IconButton(onClick = { onClickBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Desc"
                        )
                    }
                },
            )
        }

    ) { paddingValues ->
        Column(modifier.padding(paddingValues)) {
            Column(
                modifier = modifierTextField
            ) {
                OutlinedTextField(
                    modifier = modifier.fillMaxWidth(),
                    value = state.valueTitleList,
                    onValueChange = {onValueChangeTitleList(it) },
                    label = { Text("Titulo da Lista") }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 300.dp, max = 330.dp),
                    value = state.valueInput,
                    label = { Text("Lista De Compras") },
                    maxLines = 400,
                    onValueChange = {
                        onValueChangeList(it)
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words
                    )
                )
            }
            Column(
                modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp
                )
            ) {
                Row(
                    modifier
                        .fillMaxWidth()
                        .heightIn(50.dp, 55.dp),
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                keyboardController?.hide()
                                onClickTransformToList()
                            },
                            modifier = modifier.fillMaxWidth(),
                        )
                        {
                            Text("Transformar")
                        }
                    }
                    state.showSaveBottom.let { show ->
                        if (show) {
                            Spacer(modifier = modifier.width(5.dp))
                            Column(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(
                                    onClick = {
                                        onSaveMarketList()
                                    },
                                    modifier = modifier.fillMaxWidth()
                                )
                                {
                                    Text("Salvar")
                                }
                            }
                        }
                    }
                }
            }

            LazyColumn(
                modifier = modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    )
                    .align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(8.dp, CenterVertically)
            ) {
                item { Spacer(modifier = Modifier) }
                itemsIndexed(state.listItemsFormated) { index, item ->
                    RowDataFormatedComponent(
                        item = item,
                        position = index,
                        onRemoveItemTransformed = {
                            onRemoveItemTransformed(it)
                        }
                    )
                }
                item { Spacer(modifier = Modifier) }
            }

        }

    }
}


@Preview(showSystemUi = true)
@Composable
fun FormMarketListScreenShowSaveButtomDontShowSavePreview() {
    MarketListTheme {
        Surface {
            FormMarketListScreen(
                state = FormMarketUiState(
                    valueInput = "Picanha do Boi Azul\nMaça\nManga\nBanana\nCandida Bauro\nCocokito Parabueno",
                    showSaveBottom = false,
                    listItemsFormated = mutableListOf(
                        "Picanha do Boi Azul",
                        "Maça",
                        "Manga",
                        "Banana",
                        "Candida Bauro",
                        "Cocokito Parabueno"
                    )
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FormMarketListScreenShowSaveButtomPreview() {
    MarketListTheme {
        Surface {
            FormMarketListScreen(
                state = FormMarketUiState(
                    valueInput = "Maça\nManga\nBanana",
                    showSaveBottom = true,
                    listItemsFormated = mutableListOf("Maça", "Manga", "Banana")
                )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FormMarketListScreenPreview() {
    MarketListTheme {
        Surface {
            FormMarketListScreen(
                state = FormMarketUiState(
                    valueInput = "",
                    listItemsFormated = mutableListOf()
                )
            )
        }
    }
}

