package br.com.marketlist.ui.uistate

import br.com.marketlist.data.ProductItem

data class MarketListUiState(
    val products: List<ProductItem> = emptyList(),
    val isShowFinishedMarket: Boolean = false,
)