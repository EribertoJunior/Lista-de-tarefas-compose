package com.example.listacompose.domain.repository

import com.example.listacompose.data.model.Task
import kotlinx.coroutines.flow.Flow

interface ListRepository {
    suspend fun getList(): Flow<List<Task>>
}