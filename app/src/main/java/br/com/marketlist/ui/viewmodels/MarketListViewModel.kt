package br.com.marketlist.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.produceState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marketlist.data.ProductItem
import br.com.marketlist.database.ListMarketDao
import br.com.marketlist.database.ProductItemDao
import br.com.marketlist.navigation.idListArgument
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
    private val dao: ProductItemDao,
    private val daoListMarket: ListMarketDao,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(MarketListUiState())

    private val idList = savedStateHandle.get<String>(idListArgument)

    val uiState: StateFlow<MarketListUiState>
        get() = _uiState.asStateFlow()

    init {

        idList?.let {
            Log.i(TAG, "marketListScreen idList: $idList")

            viewModelScope.launch(Dispatchers.IO) {
                val titleString = daoListMarket.findTitleItem(idList.toLong())
                dao.findAllWithList(idList.toLong()).collect {productsList ->
                    _uiState.value = _uiState.value.copy(
                        products = productsList,
                        titleList =titleString

                    )
                }
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

    fun onClickDeleteAllList() {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteAll()
        }
    }

    companion object {
        const val TAG = "MarketListViewModel"
    }


}