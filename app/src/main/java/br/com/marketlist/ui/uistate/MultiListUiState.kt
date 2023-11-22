package br.com.marketlist.ui.uistate

import br.com.marketlist.data.ListMarket
import br.com.marketlist.data.ProductItem

data class MultiListUiState(
    val list: Map<ListMarket, List<ProductItem>> = emptyMap()
)