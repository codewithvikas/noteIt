package com.example.noteit

import com.example.noteit.data.DefaultTaskRepository
import com.example.noteit.ui.TaskListViewModel

class TaskListViewModelTest {
    val defaultTaskRepository: DefaultTaskRepository = DefaultTaskRepository()
    val viewModel = TaskListViewModel(defaultTaskRepository)
}