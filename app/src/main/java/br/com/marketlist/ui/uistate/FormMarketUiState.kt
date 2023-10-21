package br.com.marketlist.ui.uistate

data class FormMarketUiState(
    var valueInput: String = "",
    val showSaveBottom: Boolean = false,
    val listItemsFormated: List<String> = emptyList(),
    val onValueChangeList: (value: String) -> Unit = {},
    val onSaveMarketList: () -> Unit = {}
) {
}