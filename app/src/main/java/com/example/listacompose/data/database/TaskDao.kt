package com.example.listacompose.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.listacompose.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("Select * From Task")
    fun getTasks(): Flow<List<Task>>

    @Insert(onConflict = REPLACE)
    fun saveTask(task: Task)
}