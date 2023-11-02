package br.com.marketlist.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.marketlist.ui.uistate.FormMarketUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FormMarketListViewModel @Inject constructor(

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
                    //TODO
                },
                onRemoveItemTransformed = { position ->
//                    removeOptionListTransformed(position)
//                    val listMutable = _uiState.value.listItemsFormated
//                    listMutable.removeAt(position)
                    _uiState.value.listItemsFormated.removeAt(position)
                    _uiState.value = _uiState.value.copy(
                        listItemsFormated = _uiState.value.listItemsFormated
                    )
                }
            )
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
        Log.i(TAG, "removeOptionListTransformed: $position")
        val listnew = mutableListOf<String>()
        listnew.addAll(uiState.value.listItemsFormated)
       listnew.removeAt(position)
        _uiState.value.run {
            _uiState.value = _uiState.value.copy(
                listItemsFormated = listnew
            )
        }


    }

    companion object {
        const val TAG = "FormMarketListViewModel"
    }


}