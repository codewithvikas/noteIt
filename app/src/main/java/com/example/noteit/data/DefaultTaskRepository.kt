package com.example.noteit.data

import com.example.noteit.data.source.local.TaskDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultTaskRepository(
    private val localDataSource: TaskDao,
    private val dispatcher: CoroutineDispatcher
) : TaskRepository {

    override fun getTasksStream(): Flow<List<Task>> {
        return localDataSource.observeAll().map { tasks ->
            withContext(dispatcher) {
                tasks.toExternal()
            }
        }
    }
}