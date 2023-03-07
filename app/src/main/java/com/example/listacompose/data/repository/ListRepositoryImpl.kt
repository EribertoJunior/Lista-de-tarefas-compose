package com.example.listacompose.data.repository

import com.example.listacompose.data.dataSource.ListDataSource
import com.example.listacompose.data.model.Task
import com.example.listacompose.domain.repository.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ListRepositoryImpl(private val listDataSource: ListDataSource) : ListRepository {
    override suspend fun getList(): Flow<List<Task>> = withContext(Dispatchers.IO) {
        listDataSource.getTasks()
    }
}