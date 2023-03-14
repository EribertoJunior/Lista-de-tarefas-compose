package com.example.listacompose.ui.uistate

import com.example.listacompose.data.model.Task

sealed class ListUiState{
    object Loading: ListUiState()
    data class Success(val data: List<Task>): ListUiState()
}
