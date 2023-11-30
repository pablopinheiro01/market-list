package br.com.marketlist.ui.uistate

data class FormMarketUiState(
    var valueInput: String = "",
    val showSaveBottom: Boolean = false,
    val valueTitleList: String = "",
    val listItemsFormated: MutableList<String> = mutableListOf(),
    val onValueChangeList: (value: String) -> Unit = {},
    val onSaveMarketList: () -> Unit = {},
    val onValueChangeTitleList:(value: String) -> Unit = {}
)
