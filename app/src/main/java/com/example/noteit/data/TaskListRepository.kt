package com.example.noteit.data

import kotlinx.coroutines.flow.flowOf

class TaskListRepository {
    val taskList = listOf(
        Task("Task 1", "Description 1"),
        Task("Task 2", "Description 2")
    )
    val tasks = flowOf(taskList)
}