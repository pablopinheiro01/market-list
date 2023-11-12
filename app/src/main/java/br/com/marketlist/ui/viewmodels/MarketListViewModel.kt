package br.com.marketlist.ui.viewmodels

import androidx.compose.runtime.produceState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marketlist.data.ProductItem
import br.com.marketlist.database.ProductItemDao
import br.com.marketlist.ui.uistate.MarketListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketListViewModel @Inject constructor(
    private val dao: ProductItemDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(MarketListUiState())

    val uiState: StateFlow<MarketListUiState>
        get() = _uiState.asStateFlow()

    init {

        viewModelScope.launch(Dispatchers.IO) {
            dao.findAll().collect {
                _uiState.value = _uiState.value.copy(
                    products = it
                )
            }
        }

    }

    fun onClickBoughtItem(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val item = dao.findItem(id)
            var productsModify: List<ProductItem> = emptyList()

            item.apply {
                this.bought = !this.bought
            }

            productsModify = _uiState.value.products
                .map { itemState ->
                    if (itemState.id == item.id) itemState.copy(bought = true) else itemState
                }
            val verifiedObjects = productsModify.all { it.bought }
//           productsModify = productsModify.sortedBy { it.bought }

            _uiState.update { currentState ->
                currentState.copy(
                    products = productsModify,
                    isShowFinishedMarket = verifiedObjects
                )
            }

            dao.updateItemBought(item)
        }

    }


}