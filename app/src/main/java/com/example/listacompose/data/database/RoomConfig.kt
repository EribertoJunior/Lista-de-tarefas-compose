package com.example.listacompose.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.listacompose.data.model.Task

@Database(entities = [Task::class], version = 1)
abstract class RoomConfig : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: RoomConfig? = null

        fun getDataBase(context: Context): RoomConfig {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomConfig::class.java,
                    "task_database"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}