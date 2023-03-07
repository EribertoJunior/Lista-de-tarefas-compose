package com.example.listacompose.domain.usercase

import com.example.listacompose.data.model.Task
import com.example.listacompose.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow

class ListUserCaseImpl(private val listRepository: ListRepository): ListUserCase {
    override suspend fun invoke(): Flow<List<Task>> {
        return listRepository.getList()
    }
}