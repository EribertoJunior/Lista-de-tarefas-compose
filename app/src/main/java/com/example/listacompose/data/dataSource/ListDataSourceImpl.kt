package com.example.listacompose.data.dataSource

import com.example.listacompose.data.database.TaskDao
import com.example.listacompose.data.model.Task
import kotlinx.coroutines.flow.Flow

class ListDataSourceImpl(private val taskDao: TaskDao) : ListDataSource {
    override suspend fun getTasks(): Flow<List<Task>> {
        return taskDao.getTasks()
    }
}