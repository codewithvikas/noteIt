package com.example.noteit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.noteit.data.Task
import com.example.noteit.data.DefaultTaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class TaskListViewModel(val defaultTaskRepository: DefaultTaskRepository) : ViewModel() {
    fun addTask(task: Task) {
        defaultTaskRepository.addTask(task)
    }

    val tasks = defaultTaskRepository.tasks.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500),
        initialValue = emptyList()
    )
}

class TaskListViewModelFactory(val defaultTaskRepository: DefaultTaskRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskListViewModel(defaultTaskRepository) as T
    }
}