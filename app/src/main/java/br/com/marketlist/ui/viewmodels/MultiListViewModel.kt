package br.com.marketlist.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marketlist.data.ListMarket
import br.com.marketlist.data.ProductItem
import br.com.marketlist.database.ListMarketDao
import br.com.marketlist.database.ProductItemDao
import br.com.marketlist.ui.uistate.MultiListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MultiListViewModel @Inject constructor(
    private val listsDao: ListMarketDao,
    private val itemsDao: ProductItemDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(MultiListUiState())
    private lateinit var lists: List<ListMarket>
    private lateinit var items: List<ProductItem>

    val uiState: StateFlow<MultiListUiState>
        get() = _uiState.asStateFlow()

    init {

        viewModelScope.launch(Dispatchers.IO) {
            listsDao.findAll().combine(itemsDao.findAll()){ markets, products ->
                lists = markets
                items = products
            }.collect{
                updateListToShow()
            }
        }
    }

    fun updateListToShow() {
        var map: MutableMap<ListMarket, List<ProductItem>> = mutableMapOf()
        var itemsCopy: MutableList<ProductItem> = mutableListOf()

        if(lists.isNotEmpty() || items.isNotEmpty()){

            for (list in lists) {
                for (item in items) {
                    if (item.idListMarket == list.id) {
                        itemsCopy.add(item)
                    }
                }
                map[list] = itemsCopy.toList()
                itemsCopy.clear()
            }

            _uiState.value = _uiState.value.copy(
                list = map
            )
        }
    }


}