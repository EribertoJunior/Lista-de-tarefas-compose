package com.example.listacompose.domain.usercase

import com.example.listacompose.data.model.Task
import kotlinx.coroutines.flow.Flow

interface ListUserCase {
    suspend fun invoke(): Flow<List<Task>>
}