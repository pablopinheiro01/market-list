package br.com.marketlist.ui.uistate

data class FormMarketUiState(
    val valueInput: String = "",
    var listItemsFormated: List<String> = emptyList()
)