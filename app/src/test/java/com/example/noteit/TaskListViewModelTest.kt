package com.example.noteit

import com.example.noteit.data.TaskListRepository
import com.example.noteit.ui.TaskListViewModel

class TaskListViewModelTest {
    val taskListRepository: TaskListRepository = TaskListRepository()
    val viewModel = TaskListViewModel(taskListRepository)
}