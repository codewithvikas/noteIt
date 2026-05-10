package com.example.noteit.data

import kotlinx.coroutines.flow.Flow

/*
Interface to the LocalTask layer
 */
interface TaskRepository {
    fun getTasksStream() : Flow<List<Task>>
}