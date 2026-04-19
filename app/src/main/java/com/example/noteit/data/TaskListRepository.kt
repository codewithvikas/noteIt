package com.example.noteit.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class TaskListRepository {
    private val taskList = listOf<Task>(
        Task("Task 1", "Description 1"),
        Task("Task 2", "Description 2"),
        Task("Task 3", "Description 3"),
        Task("Task 4", "Description 4"),
        Task("Task 5", "Description 5"),
    )
    val tasks = MutableStateFlow(taskList)

    fun addTask(task: Task) {
        tasks.update { it + task }
    }
}