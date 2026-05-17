package com.example.noteit.data

import com.example.noteit.data.source.local.TaskDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.UUID

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

    override suspend fun createTask(title: String, description: String) : String {
        val taskId = withContext(dispatcher) {
            UUID.randomUUID().toString()
        }
        val task = Task(
            id = taskId,
            title = title,
            description = description
        )
        localDataSource.upsert(task.toLocal())
        return taskId
    }
}