package com.example.listacompose.data.dataSource

import com.example.listacompose.data.model.Task
import kotlinx.coroutines.flow.Flow

interface ListDataSource {
    suspend fun getTasks(): Flow<List<Task>>
}