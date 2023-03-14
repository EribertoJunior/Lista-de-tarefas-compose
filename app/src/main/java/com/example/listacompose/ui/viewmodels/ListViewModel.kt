package com.example.listacompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listacompose.domain.usercase.ListUserCase
import com.example.listacompose.ui.uistate.ListUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(private val listUserCase: ListUserCase) : ViewModel() {
    private var mutableSate: MutableStateFlow<ListUiState> = MutableStateFlow(ListUiState.Loading)
    val state get() = mutableSate.asStateFlow()

    init {
        invokeTasks()
    }

    private fun invokeTasks() {
        viewModelScope.launch {
            delay(2000L)
            listUserCase.invoke().collect {
                mutableSate.value = ListUiState.Success(data = it)
            }
        }
    }
}