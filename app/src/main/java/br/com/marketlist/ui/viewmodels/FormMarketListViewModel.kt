package br.com.marketlist.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marketlist.data.ItemsProduct
import br.com.marketlist.data.ProductItem
import br.com.marketlist.database.ProductItemDao
import br.com.marketlist.ui.uistate.FormMarketUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormMarketListViewModel @Inject constructor(
    private val dao: ProductItemDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(FormMarketUiState())

    val uiState: StateFlow<FormMarketUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onValueChangeList = { listString ->
                    _uiState.value = _uiState.value.copy(
                        valueInput = listString,
                        showSaveBottom = false,
                    )
                },
                onSaveMarketList = {
                    saveMarketList()
                }
            )
        }
    }

    fun saveMarketList() {
        val listProductItems: MutableList<ProductItem> = mutableListOf()
        _uiState.value.listItemsFormated.forEach { item ->
            listProductItems.add(ProductItem(name = item))
        }
//        dao.save(ItemsProduct(list = listProductItems))
        viewModelScope.launch(Dispatchers.IO) {
            dao.save(listProductItems)
        }
    }

    fun transformStringToItems() {

        val valueToTransform = _uiState.value.valueInput

        val listTransformed = valueToTransform.lines().filter { it.isNotBlank() }.toMutableList()

        //other tecnique to transform objects
//        val mapTransformed: Map<Int, String> = listTransformed.associateBy({ item -> listTransformed.indexOf(item) }, {item -> item})

        _uiState.value.run {
            _uiState.value = _uiState.value.copy(
                listItemsFormated = listTransformed,
                showSaveBottom = true,
            )
        }
    }

    fun removeOptionListTransformed(position: Int) {
        val temporaryListNew = mutableListOf<String>()
        temporaryListNew.addAll(uiState.value.listItemsFormated)
        temporaryListNew.removeAt(position)
        _uiState.value.run {
            _uiState.value = _uiState.value.copy(
                listItemsFormated = temporaryListNew
            )
        }
    }

    companion object {
        const val TAG = "FormMarketListViewModel"
    }


}