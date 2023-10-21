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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.marketlist.ui.theme.MarketListTheme
import br.com.marketlist.ui.uistate.FormMarketUiState
import br.com.marketlist.ui.viewmodels.FormMarketListViewModel


@Composable
fun FormMarketListScreen(
    viewModel: FormMarketListViewModel,
    state: FormMarketUiState,
    onValueChangeList: (value: String) -> Unit = {},
    onSaveMarketList: () -> Unit = {}
) {

    FormMarketListScreen(
        state = state,
        onValueChangeList = {
            onValueChangeList(it)
        },
        onClickTransformToList = {
            viewModel.transformStringToItems()
        },
        onSaveMarketList = {
            onSaveMarketList()
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FormMarketListScreen(
    modifier: Modifier = Modifier,
    state: FormMarketUiState,
    onValueChangeList: (value: String) -> Unit = {},
    onClickTransformToList: () -> Unit = {},
    onSaveMarketList: () -> Unit = {}
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val modifierTextField = if (state.showSaveBottom) {
        modifier
            .padding(start = 16.dp, top = 16.dp, bottom = 0.dp, end = 16.dp)
            .height(90.dp)
    } else {
        modifier
            .padding(start = 16.dp, top = 16.dp, bottom = 0.dp, end = 16.dp)
    }

    Scaffold { paddingValues ->
        Column(modifier.padding(paddingValues)) {
            Column(
                modifier = modifierTextField
            ) {
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
                items(state.listItemsFormated) {
                    RowDataFormatedComponent(item = it)
                }
                item { Spacer(modifier = Modifier) }
            }

        }

    }
}

@Composable
fun RowDataFormatedComponent(
    modifier: Modifier = Modifier,
    item: String = ""
) {
    Card(
        modifier = modifier
            .fillMaxWidth()

    ) {
        Row(
            modifier = modifier
                .padding(
                    top = 8.dp,
                    bottom = 8.dp
                )
                .fillMaxWidth()
        ) {
            Checkbox(
                modifier = modifier
                    .padding(start = 8.dp)
                    .align(alignment = CenterVertically)
                    .widthIn(20.dp),
                checked = false,
                onCheckedChange = {
                    //TODO
                }
            )
            Text(
                modifier = modifier
                    .align(CenterVertically),
                text = "$item"
            )
        }
    }
}

@Preview
@Composable
fun RowDataFormatedComponentPreview() {
    MarketListTheme {
        RowDataFormatedComponent(
            item = "Maça"
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun FormMarketListScreenShowSaveButtomDontShowSavePreview() {
    MarketListTheme {
        Surface {
            FormMarketListScreen(
                state = FormMarketUiState(
                    valueInput = "Maça\nManga\nBanana",
                    showSaveBottom = false,
                    listItemsFormated = listOf("Maça", "Manga", "Banana")
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
                    listItemsFormated = listOf("Maça", "Manga", "Banana")
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
                    listItemsFormated = listOf()
                )
            )
        }
    }
}