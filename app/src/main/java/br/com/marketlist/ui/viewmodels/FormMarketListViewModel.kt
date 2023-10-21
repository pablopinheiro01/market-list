package br.com.marketlist.ui.viewmodels

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

): ViewModel() {

    private val _uiState = MutableStateFlow(FormMarketUiState())

    val uiState: StateFlow<FormMarketUiState>
        get() = _uiState.asStateFlow()

    init {

        _uiState.update { currentState ->
            currentState.copy(
                onValueChangeList = { listString ->
                    _uiState.value = _uiState.value.copy(
                        valueInput = listString,
                        showSaveBottom = false
                    )
                },
                onSaveMarketList = {
                    //TODO
                }
            )

        }

    }

    fun showSaveButton(){
        if(_uiState.value.listItemsFormated.size > 1){
            _uiState.value = _uiState.value.copy(
                showSaveBottom = false
            )
        }
    }

    fun transformStringToItems() {

        val valueToTransform = _uiState.value.valueInput

        val listaTransformed = valueToTransform.lines().filter{ it.isNotBlank() }.toList()

        _uiState.value = _uiState.value.copy(
            listItemsFormated = listaTransformed,
            showSaveBottom = true
        )

    }


}