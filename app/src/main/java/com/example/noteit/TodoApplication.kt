package com.example.noteit

import android.app.Application
import androidx.room.Room
import com.example.noteit.data.source.local.TaskDao
import com.example.noteit.data.source.local.ToDoDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TodoApplication : Application() {

    lateinit var taskDao: TaskDao
    lateinit var ioDispatcher: CoroutineDispatcher

    override fun onCreate() {
        super.onCreate()
        ioDispatcher = Dispatchers.IO
        taskDao =
            Room.databaseBuilder(this, ToDoDatabase::class.java, "Tasks.db").build()
                .taskDao()
    }
}